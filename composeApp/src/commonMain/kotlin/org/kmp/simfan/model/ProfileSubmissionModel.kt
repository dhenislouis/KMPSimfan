package org.kmp.simfan.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
data class InvestmentObjectivesData(
    @SerialName("ID")
    val id: Int,
    @SerialName("Name")
    val name: String,
)

@Serializable
data class  IndustrialSectorsData(
    @SerialName("ID")
    val id: Int,
    @SerialName("Name")
    val name: String,
)
@Serializable
data class  JobTitlesData(
    @SerialName("ID")
    val id: Int,
    @SerialName("Name")
    val name: String,
)
@Serializable
data class  JobData(
    @SerialName("ID")
    val id: Int,
    @SerialName("Name")
    val name: String,
)@Serializable
data class  MonthlySalariesData(
    @SerialName("ID")
    val id: Int,
    @SerialName("Name")
    val name: String,
)
@Serializable
data class  RevenueData(
    @SerialName("ID")
    val id: Int,
    @SerialName("Name")
    val name: String,
)


