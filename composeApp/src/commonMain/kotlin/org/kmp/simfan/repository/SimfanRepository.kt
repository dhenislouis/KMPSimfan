package org.kmp.simfan.repository

import org.kmp.simfan.auth.AuthManager
import org.kmp.simfan.model.ApiResponse
import org.kmp.simfan.model.BPR
import org.kmp.simfan.model.BankAccount
import org.kmp.simfan.model.Deposit
import org.kmp.simfan.model.DepositRequest
import org.kmp.simfan.model.FirebaseTokenRequest
import org.kmp.simfan.model.IndustrialSectorsData
import org.kmp.simfan.model.InvestmentObjectivesData
import org.kmp.simfan.model.JobData
import org.kmp.simfan.model.JobTitlesData
import org.kmp.simfan.model.MonthlySalariesData
import org.kmp.simfan.model.Product
import org.kmp.simfan.model.Profile
import org.kmp.simfan.model.ProfileSubmissionRequest
import org.kmp.simfan.model.Promotion
import org.kmp.simfan.model.RevenueData
import org.kmp.simfan.model.SaldoResponse
import org.kmp.simfan.model.SignInRequest
import org.kmp.simfan.model.SignInResponse
import org.kmp.simfan.model.SignUpRequest
import org.kmp.simfan.model.SimulasiDepositoResponse
import org.kmp.simfan.model.UserProfile

import org.kmp.simfan.network.SimfanApiService

class SimfanRepository(
    private val apiService: SimfanApiService,
    private val authManager: AuthManager
) {
    // Auth
    suspend fun signIn(userId: String, password: String, rememberMe: Boolean): Result<SignInResponse> {
        return try {
            val request = SignInRequest(userId, password, rememberMe)
            val response = apiService.signIn(request)
            apiService.setToken(response.data?.accessToken)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun signUp(name: String, email: String, phone: String, password: String): Result<SignInResponse> {
        return try {
            val request = SignUpRequest(name, email, phone, password)
            val response = apiService.signUp(request)
            apiService.setToken(response.data?.accessToken)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun firebaseLogin(token: String, name: String): Result<SignInResponse> {
        return try {
            val request = FirebaseTokenRequest(token, name)
            val response = apiService.firebaseLogin(request)
            apiService.setToken(response.data?.accessToken)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun requestOTP(method: String): Result<Boolean> {
        return try {
            // Call API to request OTP
            // This needs to be implemented in the API service
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun validateOTP(otp: String): Result<Boolean> {
        return try {
            // Call API to validate OTP
            // This needs to be implemented in the API service
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun changePassword(newPassword: String): Result<Boolean> {
        return try {
            // Call API to change password
            // This needs to be implemented in the API service
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun logout() {
        authManager.logout()
    }

    fun isLoggedIn(): Boolean {
        return authManager.isLoggedIn()
    }

    // Beranda
    suspend fun getUserProfile(): Result<UserProfile> {
        return try {
            val response = apiService.getUserProfile()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getSaldo(): Result<SaldoResponse> {
        return try {
            val response = apiService.getSaldo()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getSimulasiDeposito(amount: Double, tenor: Int): Result<SimulasiDepositoResponse> {
        return try {
            val response = apiService.getSimulasiDeposito(amount, tenor)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getPromosiBeranda(): Result<List<Promotion>> {
        return try {
            val response = apiService.getPromosiBeranda()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Product
    suspend fun getProducts(): Result<List<Product>> {
        return try {
            val response = apiService.getProducts()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getProductDetail(id: UInt): Result<Product> {
        return try {
            val response = apiService.getProductDetail(id)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getBPRDetail(bprId: UInt): Result<BPR> {
        return try {
            val response = apiService.getBPRDetail(bprId)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Tambahkan metode untuk mendapatkan BPR berdasarkan nama
    suspend fun getBPRByName(bprName: String): Result<BPR> {
        return try {
            val response = apiService.getBPRByName(bprName)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Promotion
    suspend fun getPromotions(): Result<List<Promotion>> {
        return try {
            val response = apiService.getPromotions()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getPromotionDetail(id: UInt): Result<Promotion> {
        return try {
            val response = apiService.getPromotionDetail(id)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Profile
    suspend fun getProfile(): Result<Profile> {
        return try {
            val response = apiService.getProfile()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getBankAccounts(): Result<List<BankAccount>> {
        return try {
            val response = apiService.getBankAccounts()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun addBankAccount(bankName: String, accountNumber: String, accountHolder: String): Result<BankAccount> {
        return try {
            val response = apiService.addBankAccount(bankName, accountNumber, accountHolder)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Tambahkan metode yang hilang
    suspend fun setPrimaryBankAccount(id: UInt): Result<BankAccount> {
        return try {
            val response = apiService.setPrimaryBankAccount(id)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteBankAccount(id: UInt): Result<Unit> {
        return try {
            apiService.deleteBankAccount(id)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Deposit
    suspend fun applyDeposit(productId: UInt, amount: Double, tenor: Int, aro: Boolean): Result<Deposit> {
        return try {
            val userId = authManager.getUserId()?.toUInt() ?: 1u
            val request = DepositRequest(userId, productId, amount, tenor, aro)
            val response = apiService.applyDeposit(request)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun generateDepositDocument(depositId: UInt): Result<Deposit> {
        return try {
            val response = apiService.generateDepositDocument(depositId)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun requestSignDeposit(depositId: UInt): Result<String> {
        return try {
            val response = apiService.requestSignDeposit(depositId)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Profile Submission
    suspend fun getInvestmentObjectives(): Result<ApiResponse<InvestmentObjectivesData>> {
        return try {
            val response = apiService.getInvestmentObjectives()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getRevenues(): Result<ApiResponse<RevenueData>> {
        return try {
            val response = apiService.getRevenues()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getJobs(): Result<ApiResponse<JobData>> {
        return try {
            val response = apiService.getJobs()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getJobTitles(): Result<ApiResponse<JobTitlesData>> {
        return try {
            val response = apiService.getJobTitles()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getMonthlySalaries(): Result<ApiResponse<MonthlySalariesData>> {
        return try {
            val response = apiService.getMonthlySalaries()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getIndustrialSectors(): Result<ApiResponse<IndustrialSectorsData>> {
        return try {
            val response = apiService.getIndustrialSectors()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun submitProfile(request: ProfileSubmissionRequest): Result<Profile> {
        return try {
            val response = apiService.submitProfile(request)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}