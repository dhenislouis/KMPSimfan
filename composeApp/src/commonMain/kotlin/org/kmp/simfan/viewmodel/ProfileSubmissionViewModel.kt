package org.kmp.simfan.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.kmp.simfan.auth.AuthManager
import org.kmp.simfan.model.Profile
import org.kmp.simfan.network.IndustrialSector
import org.kmp.simfan.network.InvestmentObjective
import org.kmp.simfan.network.Job
import org.kmp.simfan.network.JobTitle
import org.kmp.simfan.network.MonthlySalary
import org.kmp.simfan.network.ProfileSubmissionRequest
import org.kmp.simfan.network.Revenue
import org.kmp.simfan.network.SimfanApiService
import org.kmp.simfan.repository.SimfanRepository

class ProfileSubmissionViewModel : ViewModel() {
    private val authManager = AuthManager()
    private val repository = SimfanRepository(
        apiService = SimfanApiService { authManager.getToken() },
        authManager = authManager
    )

    private val _investmentObjectives: MutableState<List<InvestmentObjective>> = mutableStateOf(emptyList())
    val investmentObjectives: State<List<InvestmentObjective>> = _investmentObjectives

    private val _revenues: MutableState<List<Revenue>> = mutableStateOf(emptyList())
    val revenues: State<List<Revenue>> = _revenues

    private val _jobs: MutableState<List<Job>> = mutableStateOf(emptyList())
    val jobs: State<List<Job>> = _jobs

    private val _jobTitles: MutableState<List<JobTitle>> = mutableStateOf(emptyList())
    val jobTitles: State<List<JobTitle>> = _jobTitles

    private val _monthlySalaries: MutableState<List<MonthlySalary>> = mutableStateOf(emptyList())
    val monthlySalaries: State<List<MonthlySalary>> = _monthlySalaries

    private val _industrialSectors: MutableState<List<IndustrialSector>> = mutableStateOf(emptyList())
    val industrialSectors: State<List<IndustrialSector>> = _industrialSectors

    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _error: MutableState<String?> = mutableStateOf(null)
    val error: State<String?> = _error

    private val _submissionResult: MutableState<Profile?> = mutableStateOf(null)
    val submissionResult: State<Profile?> = _submissionResult

    init {
        loadProfileSubmissionData()
    }

    fun loadProfileSubmissionData() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                // Load investment objectives
                val investmentObjectivesResult = repository.getInvestmentObjectives()
                if (investmentObjectivesResult.isSuccess) {
                    _investmentObjectives.value = investmentObjectivesResult.getOrNull() ?: emptyList()
                } else {
                    _error.value = investmentObjectivesResult.exceptionOrNull()?.message ?: "Failed to load investment objectives"
                }

                // Load revenues
                val revenuesResult = repository.getRevenues()
                if (revenuesResult.isSuccess) {
                    _revenues.value = revenuesResult.getOrNull() ?: emptyList()
                } else {
                    _error.value = revenuesResult.exceptionOrNull()?.message ?: "Failed to load revenues"
                }

                // Load jobs
                val jobsResult = repository.getJobs()
                if (jobsResult.isSuccess) {
                    _jobs.value = jobsResult.getOrNull() ?: emptyList()
                } else {
                    _error.value = jobsResult.exceptionOrNull()?.message ?: "Failed to load jobs"
                }

                // Load job titles
                val jobTitlesResult = repository.getJobTitles()
                if (jobTitlesResult.isSuccess) {
                    _jobTitles.value = jobTitlesResult.getOrNull() ?: emptyList()
                } else {
                    _error.value = jobTitlesResult.exceptionOrNull()?.message ?: "Failed to load job titles"
                }

                // Load monthly salaries
                val monthlySalariesResult = repository.getMonthlySalaries()
                if (monthlySalariesResult.isSuccess) {
                    _monthlySalaries.value = monthlySalariesResult.getOrNull() ?: emptyList()
                } else {
                    _error.value = monthlySalariesResult.exceptionOrNull()?.message ?: "Failed to load monthly salaries"
                }

                // Load industrial sectors
                val industrialSectorsResult = repository.getIndustrialSectors()
                if (industrialSectorsResult.isSuccess) {
                    _industrialSectors.value = industrialSectorsResult.getOrNull() ?: emptyList()
                } else {
                    _error.value = industrialSectorsResult.exceptionOrNull()?.message ?: "Failed to load industrial sectors"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Failed to load profile submission data"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun submitProfile(
        investmentObjectiveId: UInt,
        revenueId: UInt,
        jobId: UInt,
        jobTitleId: UInt,
        monthlySalaryId: UInt,
        industrialSectorId: UInt,
        workPhone: String,
        workAddress: String,
        motherMaidenName: String
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val request = ProfileSubmissionRequest(
                    investmentObjectiveId = investmentObjectiveId,
                    revenueId = revenueId,
                    jobId = jobId,
                    jobTitleId = jobTitleId,
                    monthlySalaryId = monthlySalaryId,
                    industrialSectorId = industrialSectorId,
                    workPhone = workPhone,
                    workAddress = workAddress,
                    motherMaidenName = motherMaidenName
                )
                val result = repository.submitProfile(request)
                if (result.isSuccess) {
                    _submissionResult.value = result.getOrNull()
                } else {
                    _error.value = result.exceptionOrNull()?.message ?: "Failed to submit profile"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Failed to submit profile"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun refresh() {
        loadProfileSubmissionData()
    }

    fun clearSubmissionResult() {
        _submissionResult.value = null
    }
}