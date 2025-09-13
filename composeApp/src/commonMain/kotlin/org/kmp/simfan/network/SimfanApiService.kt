package org.kmp.simfan.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.kmp.simfan.model.BPR
import org.kmp.simfan.model.BankAccount
import org.kmp.simfan.model.Deposit
import org.kmp.simfan.model.DepositRequest
import org.kmp.simfan.model.FirebaseTokenRequest
import org.kmp.simfan.model.Product
import org.kmp.simfan.model.Profile
import org.kmp.simfan.model.Promotion
import org.kmp.simfan.model.SaldoResponse
import org.kmp.simfan.model.SignInRequest
import org.kmp.simfan.model.SignInResponse
import org.kmp.simfan.model.SignUpRequest
import org.kmp.simfan.model.SimulasiDepositoResponse
import org.kmp.simfan.model.UserProfile

class SimfanApiService(
    private val tokenProvider: () -> String? = { null }
) {
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
        defaultRequest {
            header("Content-Type", "application/json")
            tokenProvider()?.let { token ->
                header("Authorization", "Bearer $token")
            }
        }
    }

    // Base URL - sesuaikan dengan konfigurasi backend
    private val baseUrl = "http://10.0.2.2:8080/v1"

    // Auth
    suspend fun signIn(request: SignInRequest): SignInResponse {
        return client.post("$baseUrl/mobile/sign-in") {
            setBody(request)
        }.body()
    }

    suspend fun signUp(request: SignUpRequest): SignInResponse {
        return client.post("$baseUrl/mobile/sign-up") {
            setBody(request)
        }.body()
    }

    suspend fun firebaseLogin(request: FirebaseTokenRequest): SignInResponse {
        return client.post("$baseUrl/mobile/auth/verify") {
            setBody(request)
        }.body()
    }

    suspend fun requestOTP(method: String): Boolean {
        return client.get("$baseUrl/mobile/otp-request") {
            parameter("method", method)
        }.body()
    }

    suspend fun validateOTP(otp: String): Boolean {
        return client.post("$baseUrl/mobile/validate-otp") {
            setBody(mapOf("otp" to otp))
        }.body()
    }

    suspend fun changePassword(newPassword: String): Boolean {
        return client.get("$baseUrl/mobile/change-password") {
            parameter("new_password", newPassword)
        }.body()
    }

    // Beranda
    suspend fun getUserProfile(): UserProfile {
        return client.get("$baseUrl/mobile/beranda/user-profile").body()
    }

    suspend fun getSaldo(): SaldoResponse {
        return client.get("$baseUrl/mobile/beranda/saldo").body()
    }

    suspend fun getSimulasiDeposito(amount: Double, tenor: Int): SimulasiDepositoResponse {
        return client.get("$baseUrl/mobile/beranda/simulasi-deposito") {
            parameter("amount", amount)
            parameter("tenor", tenor)
        }.body()
    }

    suspend fun getPromosiBeranda(): List<Promotion> {
        return client.get("$baseUrl/mobile/beranda/promosi").body()
    }

    // Product
    suspend fun getProducts(): List<Product> {
        return client.get("$baseUrl/mobile/product").body()
    }

    suspend fun getProductDetail(id: UInt): Product {
        return client.get("$baseUrl/mobile/product/$id").body()
    }

    suspend fun getSubDetailProduct(id: UInt): Product {
        return client.get("$baseUrl/mobile/product/sub-detail-product/$id").body()
    }

    suspend fun getBPRDetail(bprId: UInt): BPR {
        return client.get("$baseUrl/mobile/bpr/$bprId").body()
    }

    // Tambahkan metode untuk mendapatkan BPR berdasarkan nama
    suspend fun getBPRByName(bprName: String): BPR {
        return client.get("$baseUrl/mobile/bpr") {
            parameter("name", bprName)
        }.body()
    }

    // Promotion
    suspend fun getPromotions(): List<Promotion> {
        return client.get("$baseUrl/mobile/promotion").body()
    }

    suspend fun getPromotionDetail(id: UInt): Promotion {
        return client.get("$baseUrl/mobile/promotion/$id").body()
    }

    // Profile Setting
    suspend fun getProfile(): Profile {
        return client.get("$baseUrl/mobile/profile-setting").body()
    }

    suspend fun getBankAccounts(): List<BankAccount> {
        return client.get("$baseUrl/mobile/profile-setting/akun-bank").body()
    }

    suspend fun addBankAccount(bankName: String, accountNumber: String, accountHolder: String): BankAccount {
        return client.post("$baseUrl/mobile/profile-setting/akun-bank") {
            setBody(mapOf(
                "bank_name" to bankName,
                "account_number" to accountNumber,
                "account_holder" to accountHolder
            ))
        }.body()
    }

    suspend fun setPrimaryBankAccount(id: UInt): BankAccount {
        return client.patch("$baseUrl/mobile/profile-setting/akun-bank/$id") {
            setBody(mapOf(
                "is_primary" to true
            ))
        }.body()
    }

    suspend fun deleteBankAccount(id: UInt) {
        client.delete("$baseUrl/mobile/profile-setting/akun-bank/$id")
    }

    // Deposit
    suspend fun applyDeposit(request: DepositRequest): Deposit {
        return client.post("$baseUrl/mobile/deposit/apply") {
            setBody(request)
        }.body()
    }

    suspend fun generateDepositDocument(id: UInt): Deposit {
        return client.post("$baseUrl/mobile/deposit/$id/document").body()
    }

    // Perbaikan untuk requestSignDeposit
    suspend fun requestSignDeposit(id: UInt): String {
        val response = client.post("$baseUrl/mobile/deposit/$id/sign-request")
        return response.body<JsonObject>()["sign_url"]?.jsonPrimitive?.content ?: ""
    }

    // Perbaikan untuk getDepositSignStatus
    suspend fun getDepositSignStatus(id: UInt): String {
        val response = client.get("$baseUrl/mobile/deposit/$id/sign-status")
        return response.body<JsonObject>()["status"]?.jsonPrimitive?.content ?: ""
    }

    // Profile Submission
    suspend fun getInvestmentObjectives(): List<InvestmentObjective> {
        return client.get("$baseUrl/mobile/profile-submission/list-investment-objectives").body()
    }

    suspend fun getRevenues(): List<Revenue> {
        return client.get("$baseUrl/mobile/profile-submission/list-revenues").body()
    }

    suspend fun getJobs(): List<Job> {
        return client.get("$baseUrl/mobile/profile-submission/list-jobs").body()
    }

    suspend fun getJobTitles(): List<JobTitle> {
        return client.get("$baseUrl/mobile/profile-submission/list-job-titles").body()
    }

    suspend fun getMonthlySalaries(): List<MonthlySalary> {
        return client.get("$baseUrl/mobile/profile-submission/list-monthly-salaries").body()
    }

    suspend fun getIndustrialSectors(): List<IndustrialSector> {
        return client.get("$baseUrl/mobile/profile-submission/list-industrial-sectors").body()
    }

    suspend fun submitProfile(request: ProfileSubmissionRequest): Profile {
        return client.post("$baseUrl/mobile/profile-submission") {
            setBody(request)
        }.body()
    }
}

// Data class untuk profile submission
@Serializable
data class ProfileSubmissionRequest(
    val investmentObjectiveId: UInt,
    val revenueId: UInt,
    val jobId: UInt,
    val jobTitleId: UInt,
    val monthlySalaryId: UInt,
    val industrialSectorId: UInt,
    val workPhone: String,
    val workAddress: String,
    val motherMaidenName: String
)

@Serializable
data class InvestmentObjective(
    val id: UInt,
    val name: String
)

@Serializable
data class Revenue(
    val id: UInt,
    val name: String
)

@Serializable
data class Job(
    val id: UInt,
    val name: String
)

@Serializable
data class JobTitle(
    val id: UInt,
    val name: String
)

@Serializable
data class MonthlySalary(
    val id: UInt,
    val range: String
)

@Serializable
data class IndustrialSector(
    val id: UInt,
    val name: String
)