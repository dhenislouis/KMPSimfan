package org.kmp.simfan.presentation.profileSubmission

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import org.kmp.simfan.auth.AuthManager
import org.kmp.simfan.model.ApiResponse
import org.kmp.simfan.model.IndustrialSectorsData
import org.kmp.simfan.model.InvestmentObjectivesData
import org.kmp.simfan.model.JobData
import org.kmp.simfan.model.JobTitlesData
import org.kmp.simfan.model.MonthlySalariesData
import org.kmp.simfan.model.RevenueData
import org.kmp.simfan.network.SimfanApiService
import org.kmp.simfan.repository.SimfanRepository
private const val TAG = "ProfileSubmissionVM"
class ProfileSubmissionViewModel : ViewModel() {
    private val authManager = AuthManager()
    private val repository = SimfanRepository(
        apiService = SimfanApiService { authManager.getToken() },
        authManager = authManager
    )

    // States untuk UI (Loading dan Error)
    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage

    // States untuk menampung list data dari API
    private val _investmentObjectives = mutableStateOf<List<InvestmentObjectivesData>>(emptyList())
    val investmentObjectives: State<List<InvestmentObjectivesData>> = _investmentObjectives

    private val _revenues = mutableStateOf<List<RevenueData>>(emptyList())
    val revenues: State<List<RevenueData>> = _revenues

    private val _jobs = mutableStateOf<List<JobData>>(emptyList())
    val jobs: State<List<JobData>> = _jobs

    private val _jobTitles = mutableStateOf<List<JobTitlesData>>(emptyList())
    val jobTitles: State<List<JobTitlesData>> = _jobTitles

    private val _monthlySalaries = mutableStateOf<List<MonthlySalariesData>>(emptyList())
    val monthlySalaries: State<List<MonthlySalariesData>> = _monthlySalaries

    private val _industrialSectors = mutableStateOf<List<IndustrialSectorsData>>(emptyList())
    val industrialSectors: State<List<IndustrialSectorsData>> = _industrialSectors

    init {
        fetchInitialData()
    }

    private fun fetchInitialData() {
        println("ProfileSubmissionVM: Mulai mengambil data...")
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {

                val investmentObjectivesDeferred = async { repository.getInvestmentObjectives() }
                val revenuesDeferred = async { repository.getRevenues() }
                val jobsDeferred = async { repository.getJobs() }
                val jobTitlesDeferred = async { repository.getJobTitles() }
                val monthlySalariesDeferred = async { repository.getMonthlySalaries() }
                val industrialSectorsDeferred = async { repository.getIndustrialSectors() }


                val investmentObjectivesResult = investmentObjectivesDeferred.await()
                val revenuesResult = revenuesDeferred.await()
                val jobsResult = jobsDeferred.await()
                val jobTitlesResult = jobTitlesDeferred.await()
                val monthlySalariesResult = monthlySalariesDeferred.await()
                val industrialSectorsResult = industrialSectorsDeferred.await()

                val allResults = listOf(
                    investmentObjectivesResult,
                    revenuesResult,
                    jobsResult,
                    jobTitlesResult,
                    monthlySalariesResult,
                    industrialSectorsResult
                )

                val firstErrorResult = allResults.firstOrNull { it.isFailure }
                if (firstErrorResult != null) {
                    val errorMsg = firstErrorResult.exceptionOrNull()?.message ?: "Gagal memuat data"
                    println("ProfileSubmissionVM: Gagal! Pesan: $errorMsg") // <-- Gunakan println
                    _errorMessage.value = errorMsg
                } else {
                    println("ProfileSubmissionVM: Berhasil!")
                    _investmentObjectives.value = investmentObjectivesResult.getOrNull()?.data ?: emptyList()
                    _revenues.value = revenuesResult.getOrNull()?.data ?: emptyList()
                    _jobs.value = jobsResult.getOrNull()?.data ?: emptyList()
                    _jobTitles.value = jobTitlesResult.getOrNull()?.data ?: emptyList()
                    _monthlySalaries.value = monthlySalariesResult.getOrNull()?.data ?: emptyList()
                    _industrialSectors.value = industrialSectorsResult.getOrNull()?.data ?: emptyList()
                }

            } catch (e: Exception) {
                _errorMessage.value = "Terjadi kesalahan: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
