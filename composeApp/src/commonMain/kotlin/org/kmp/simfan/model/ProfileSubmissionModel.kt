package org.kmp.simfan.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// Data class untuk profile submission
/**
 * STEP: privy
 * Data class ini kosong karena kedua field (ktp & selfie) adalah file.
 * File akan dikirim terpisah di layer repository/network.
 */
//@Serializable
//data class PrivyStepRequest(
//
//)

/**
 * STEP: npwp
 * Hanya berisi nomor NPWP. File gambar NPWP akan dikirim terpisah.
 */
@Serializable
data class NpwpStepRequest(
    @SerialName("npwp_number")
    val npwpNumber: String
)

/**
 * STEP: identity
 * Berisi semua data identitas diri.
 * Saya mengganti tipe data ID dari UInt ke Int karena lebih umum.
 * Serializer akan mengubahnya menjadi String saat dikirim sebagai form-data.
 */
@Serializable
data class IdentityStepRequest(
    @SerialName("investment_objective_id")
    val investmentObjectiveId: Int,

    @SerialName("revenue_id")
    val revenueId: Int,

    @SerialName("job_id")
    val jobId: Int,

    @SerialName("job_title_id")
    val jobTitleId: Int,

    @SerialName("monthly_salary_id")
    val monthlySalaryId: Int,

    @SerialName("industrial_sector_id")
    val industrialSectorId: Int,

    @SerialName("work_phone")
    val workPhone: String,

    @SerialName("work_address")
    val workAddress: String,

    @SerialName("mother_maiden_name")
    val motherMaidenName: String
)

/**
 * STEP: pin & confirm-pin
 * Model ini bisa digunakan untuk kedua step yang berhubungan dengan PIN.
 */
@Serializable
data class PinStepRequest(
    @SerialName("pin")
    val pin: String
)

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
    @SerialName("Range")
    val range: String,
)
@Serializable
data class  RevenueData(
    @SerialName("ID")
    val id: Int,
    @SerialName("Name")
    val name: String,
)


@Serializable
data class ProfileSubmitResponse(
    val status: Boolean = false,
    val message: String? = null
)


