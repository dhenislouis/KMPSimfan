package org.kmp.simfan.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.delete
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.Parameters
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.kmp.simfan.model.ApiResponse
import org.kmp.simfan.model.BPR
import org.kmp.simfan.model.BankAccount
import org.kmp.simfan.model.Deposit
import org.kmp.simfan.model.DepositRequest
import org.kmp.simfan.model.FirebaseTokenRequest
import org.kmp.simfan.model.IdentityStepRequest
import org.kmp.simfan.model.IndustrialSectorsData
import org.kmp.simfan.model.InvestmentObjectivesData
import org.kmp.simfan.model.JobData
import org.kmp.simfan.model.JobTitlesData
import org.kmp.simfan.model.MonthlySalariesData
import org.kmp.simfan.model.NpwpStepRequest
import org.kmp.simfan.model.PinStepRequest
import org.kmp.simfan.model.Product
import org.kmp.simfan.model.Profile
import org.kmp.simfan.model.ProfileSubmissionRequest
import org.kmp.simfan.model.ProfileSubmitResponse
import org.kmp.simfan.model.Promotion
import org.kmp.simfan.model.RevenueData
import org.kmp.simfan.model.SaldoResponse
import org.kmp.simfan.model.SignInRequest
import org.kmp.simfan.model.SignInResponse
import org.kmp.simfan.model.SignUpRequest
import org.kmp.simfan.model.SimulasiDepositoResponse
import org.kmp.simfan.model.UserProfile
import kotlin.concurrent.Volatile
import org.kmp.simfan.getAppEnvironment

class SimfanApiService(
    private val tokenProvider: () -> String? = { null }
) {

//    @Volatile
//    private var authToken: String? = null
    private val noAuthClient = HttpClient {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true; isLenient = true })
        }
        install(Logging) { level = LogLevel.ALL }
        followRedirects = true
        defaultRequest {
            header("Content-Type", "application/json")
        }
    }
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }
        install(Logging) {
            level = LogLevel.ALL
        }
        followRedirects = true
        install(Auth) {
            bearer {
                // Blok ini akan dijalankan SETIAP KALI ada request
                loadTokens {
                    // Ambil token terbaru dari penyimpanan Anda
                    val token = tokenProvider() // <--- GUNAKAN tokenProvider DI SINI
                    print("ini namanya token $token")

                    // Jika token-nya ada, gunakan sebagai Bearer Token
                    if (!token.isNullOrBlank()) {
                        BearerTokens(accessToken = token, refreshToken = "")
                    } else {
                        null
                    }

                }
            }
            }
//        defaultRequest {
//
//        }
    }

//    fun setToken(token: String?) {
//        authToken = token
//    }
//
//    fun clearToken() {
//        authToken = null
//    }


    // Base URL - sesuaikan dengan konfigurasi backend
    private val baseUrl = "${getAppEnvironment().baseUrl}/v1/mobile"

    // Auth
    suspend fun signIn(request: SignInRequest): SignInResponse {
        println("======== RequestSignin: $request ========")
        val resp: SignInResponse = noAuthClient.post("$baseUrl/sign-in") {
            setBody(request)
        }.body()
//        authToken = resp.data?.accessToken
        return resp
    }

    suspend fun signUp(request: SignUpRequest): SignInResponse {
        return noAuthClient.post("$baseUrl/sign-up") {
            setBody(request)
        }.body()
    }

    suspend fun firebaseLogin(request: FirebaseTokenRequest): SignInResponse {
        return noAuthClient.post("$baseUrl/auth/verify") {
            setBody(request)
        }.body()
    }

    suspend fun requestOTP(method: String): Boolean {
        return client.get("$baseUrl/otp-request") {
            parameter("method", method)
        }.body()
    }

    suspend fun validateOTP(otp: String): Boolean {
        return client.post("$baseUrl/validate-otp") {
            setBody(mapOf("otp" to otp))
        }.body()
    }

    suspend fun changePassword(newPassword: String): Boolean {
        return client.get("$baseUrl/change-password") {
            parameter("new_password", newPassword)
        }.body()
    }

    // Beranda
    suspend fun getUserProfile(): UserProfile {
        return client.get("$baseUrl/beranda/user-profile").body()
    }

    suspend fun getSaldo(): SaldoResponse {
        return client.get("$baseUrl/beranda/saldo").body()
    }

    suspend fun getSimulasiDeposito(amount: Double, tenor: Int): SimulasiDepositoResponse {
        return client.get("$baseUrl/beranda/simulasi-deposito") {
            parameter("amount", amount)
            parameter("tenor", tenor)
        }.body()
    }

    suspend fun getPromosiBeranda(): List<Promotion> {
        return client.get("$baseUrl/beranda/promosi").body()
    }

    // Product
    suspend fun getProducts(): List<Product> {
        return client.get("$baseUrl/product").body()
    }

    suspend fun getProductDetail(id: UInt): Product {
        return client.get("$baseUrl/product/$id").body()
    }

    suspend fun getSubDetailProduct(id: UInt): Product {
        return client.get("$baseUrl/product/sub-detail-product/$id").body()
    }

    suspend fun getBPRDetail(bprId: UInt): BPR {
        return client.get("$baseUrl/bpr/$bprId").body()
    }

    // Tambahkan metode untuk mendapatkan BPR berdasarkan nama
    suspend fun getBPRByName(bprName: String): BPR {
        return client.get("$baseUrl/bpr") {
            parameter("name", bprName)
        }.body()
    }

    // Promotion
    suspend fun getPromotions(): List<Promotion> {
        return client.get("$baseUrl/promotion").body()
    }

    suspend fun getPromotionDetail(id: UInt): Promotion {
        return client.get("$baseUrl/promotion/$id").body()
    }

    // Profile Setting
    suspend fun getProfile(): Profile {
        return client.get("$baseUrl/profile-setting").body()
    }

    suspend fun getBankAccounts(): List<BankAccount> {
        return client.get("$baseUrl/profile-setting/akun-bank").body()
    }

    suspend fun addBankAccount(bankName: String, accountNumber: String, accountHolder: String): BankAccount {
        return client.post("$baseUrl/profile-setting/akun-bank") {
            setBody(mapOf(
                "bank_name" to bankName,
                "account_number" to accountNumber,
                "account_holder" to accountHolder
            ))
        }.body()
    }

    suspend fun setPrimaryBankAccount(id: UInt): BankAccount {
        return client.patch("$baseUrl/profile-setting/akun-bank/$id") {
            setBody(mapOf(
                "is_primary" to true
            ))
        }.body()
    }

    suspend fun deleteBankAccount(id: UInt) {
        client.delete("$baseUrl/profile-setting/akun-bank/$id")
    }

    // Deposit
    suspend fun applyDeposit(request: DepositRequest): Deposit {
        return client.post("$baseUrl/deposit/apply") {
            setBody(request)
        }.body()
    }

    suspend fun generateDepositDocument(id: UInt): Deposit {
        return client.post("$baseUrl/deposit/$id/document").body()
    }

    // Perbaikan untuk requestSignDeposit
    suspend fun requestSignDeposit(id: UInt): String {
        val response = client.post("$baseUrl/deposit/$id/sign-request")
        return response.body<JsonObject>()["sign_url"]?.jsonPrimitive?.content ?: ""
    }

    // Perbaikan untuk getDepositSignStatus
    suspend fun getDepositSignStatus(id: UInt): String {
        val response = client.get("$baseUrl/deposit/$id/sign-status")
        return response.body<JsonObject>()["status"]?.jsonPrimitive?.content ?: ""
    }

    // Profile Submission
    suspend fun getInvestmentObjectives():  ApiResponse<List<InvestmentObjectivesData>> {
        return client.get("$baseUrl/profile-submission/list-investment-objectives").body()
    }

    suspend fun getRevenues(): ApiResponse<List<RevenueData>> {
        return client.get("$baseUrl/profile-submission/list-revenues").body()
    }

    suspend fun getJobs():ApiResponse<List<JobData>> {
        return client.get("$baseUrl/profile-submission/list-jobs").body()
    }

    suspend fun getJobTitles(): ApiResponse<List<JobTitlesData>> {
        return client.get("$baseUrl/profile-submission/list-job-titles").body()
    }

    suspend fun getMonthlySalaries(): ApiResponse<List<MonthlySalariesData>> {
        return client.get("$baseUrl/profile-submission/list-monthly-salaries").body()
    }

    suspend fun getIndustrialSectors(): ApiResponse<List<IndustrialSectorsData>> {
        return client.get("$baseUrl/profile-submission/list-industrial-sectors").body()
    }

    suspend fun submitProfile(request: ProfileSubmissionRequest): Profile {
        return client.post("$baseUrl/profile-submission") {
            setBody(request)
        }.body()
    }
    suspend fun submitIdentityStep(request: IdentityStepRequest): ProfileSubmitResponse {
        return client.post("$baseUrl/profile-submission/") {
            url {
                parameters.append("step", "identity")
            }
            // Menggunakan FormDataContent untuk mengirim data teks (x-www-form-urlencoded)
            setBody(FormDataContent(Parameters.build {
                append("investment_objective_id", request.investmentObjectiveId.toString())
                append("revenue_id", request.revenueId.toString())
                append("job_id", request.jobId.toString())
                // ... append semua field lainnya dari request
                append("mother_maiden_name", request.motherMaidenName)
            }))
        }.body()
    }

    suspend fun submitPrivyStep(ktpBytes: ByteArray?, selfieBytes: ByteArray?): ProfileSubmitResponse {
        return client.post("$baseUrl/profile-submission/") {
            url {
                parameters.append("step", "privy")
            }
            setBody(MultiPartFormDataContent(formData {
                if (ktpBytes != null) {
                    append("ktp", ktpBytes, Headers.build {
                        append(HttpHeaders.ContentType, "image/jpeg")
                        append(HttpHeaders.ContentDisposition, "filename=\"ktp.jpg\"")
                    })
                }
                if (selfieBytes != null) {
                    append("selfie", selfieBytes, Headers.build {
                        append(HttpHeaders.ContentType, "image/jpeg")
                        append(HttpHeaders.ContentDisposition, "filename=\"selfie.jpg\"")
                    })
                }
            }))
        }.body()
    }

    suspend fun submitNpwpStep(npwp: String, npwpImageBytes: ByteArray?): ProfileSubmitResponse {
        return client.post("$baseUrl/profile-submission/") {
            url {
                parameters.append("step", "npwp")
            }
            setBody(MultiPartFormDataContent(formData {
                // bagian data teks
                append("npwp_number", npwp)
                // bagian file
                if (npwpImageBytes != null) {
                    append("npwp_imgurl", npwpImageBytes, Headers.build {
                        append(HttpHeaders.ContentType, "image/jpeg")
                        append(HttpHeaders.ContentDisposition, "filename=\"npwp.jpg\"")
                    })
                }
            }))
        }.body()
    }

    // Fungsi untuk step 'pin'
    suspend fun submitPinStep(pin: String): ProfileSubmitResponse {
        return client.post("$baseUrl/profile-submission/") {
            url {
                parameters.append("step", "pin")
            }
            setBody(FormDataContent(Parameters.build {
                append("pin", pin)
            }))
        }.body()
    }
    suspend fun submitPinConfirmStep(pin: String): ProfileSubmitResponse {
        return client.post("$baseUrl/profile-submission/") {
            url {
                parameters.append("step", "confirm-pin")
            }
            setBody(FormDataContent(Parameters.build {
                append("pin", pin)
            }))
        }.body()
    }
suspend fun submitProfileStep(
    step: String,
    npwpNumber: String? = null,
    ktpFile: Pair<String, ByteArray>? = null,    // filename + bytes
    selfieFile: Pair<String, ByteArray>? = null,
    npwpFile: Pair<String, ByteArray>? = null,
    identity: IdentityStepRequest? = null,
    pin: String? = null
): ApiResponse<Unit> {
    val client = HttpClient() {
        install(ContentNegotiation) {
            json()
        }
    }

    return client.submitFormWithBinaryData(
        url = "$baseUrl/profile-submission?step=$step",
        formData = formData {
            // === STEP privy ===
            ktpFile?.let { (filename, bytes) ->
                append("ktp", bytes, Headers.build {
                    append(HttpHeaders.ContentType, "image/jpeg")
                    append(HttpHeaders.ContentDisposition, "filename=\"$filename\"")
                })
            }
            selfieFile?.let { (filename, bytes) ->
                append("selfie", bytes, Headers.build {
                    append(HttpHeaders.ContentType, "image/jpeg")
                    append(HttpHeaders.ContentDisposition, "filename=\"$filename\"")
                })
            }

            // === STEP npwp ===
            println("npwp number $npwpNumber")
            println("file npwp $npwpFile")

            npwpNumber?.let { append("npwp_number", it) }
            npwpFile?.let { (filename, bytes) ->
                append("npwp_imgurl", bytes, Headers.build {
                    append(HttpHeaders.ContentType, "image/jpeg")
                    append(HttpHeaders.ContentDisposition, "filename=\"$filename\"")
                })
            }


            // === STEP identity ===
            identity?.let {
                append("investment_objective_id", it.investmentObjectiveId.toString())
                append("revenue_id", it.revenueId.toString())
                append("job_id", it.jobId.toString())
                append("job_title_id", it.jobTitleId.toString())
                append("monthly_salary_id", it.monthlySalaryId.toString())
                append("industrial_sector_id", it.industrialSectorId.toString())
                append("work_phone", it.workPhone)
                append("work_address", it.workAddress)
                append("mother_maiden_name", it.motherMaidenName)
            }

            // === STEP pin / confirm-pin ===
            pin?.let { append("pin", it) }
        }
    ).body()
}

}


