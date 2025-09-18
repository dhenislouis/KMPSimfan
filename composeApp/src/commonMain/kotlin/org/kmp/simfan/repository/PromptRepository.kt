package org.kmp.simfan.repository

import org.kmp.simfan.repository.prompt.PromptAsset
import simfan.composeapp.generated.resources.Res

interface PromptRepository {
    suspend fun prompt(file: PromptAsset, type: DocumentType): String
}

class PromptRepositoryImpl : PromptRepository {
    override suspend fun prompt(file: PromptAsset, type: DocumentType): String {
        return when (type) {
            DocumentType.KTP -> getKtpPrompt(file)
            DocumentType.NPWP -> getNpwpPrompt(file)
        }
    }

    private suspend fun getKtpPrompt(file: PromptAsset): String {
        return try {
            Res.readBytes("files/${file.name()}").decodeToString()
        } catch (e: Exception) {
            "Ekstrak informasi berikut dari gambar KTP:\n" +
                    "- provinsi: Provinsi\n" +
                    "- kabupaten: Kabupaten/Kota\n" +
                    "- nik: Nomor Induk Kependudukan\n" +
                    "- nama: Nama lengkap\n" +
                    "- tempat_tanggal_lahir: Tempat dan tanggal lahir\n" +
                    "- jenis_kelamin: Jenis kelamin\n" +
                    "- alamat: Alamat\n" +
                    "- rt_rw: RT/RW\n" +
                    "- kelurahan_desa: Kelurahan/Desa\n" +
                    "- kecamatan: Kecamatan\n" +
                    "- agama: Agama\n" +
                    "- status_perkawinan: Status perkawinan\n" +
                    "- pekerjaan: Pekerjaan\n" +
                    "- kewarganegaraan: Kewarganegaraan\n\n" +
                    "Kembalikan hasil dalam format JSON dengan field-field di atas."
        }
    }

    private suspend fun getNpwpPrompt(file: PromptAsset): String {
        return try {
            Res.readBytes("files/${file.name()}").decodeToString()
        } catch (e: Exception) {
            "Ekstrak informasi berikut dari gambar NPWP:\n" +
                    "- npwp_number: Nomor NPWP\n" +
                    "- nama: Nama pemilik NPWP\n" +
                    "- alamat: Alamat pemilik NPWP\n\n" +
                    "Kembalikan hasil dalam format JSON dengan field-field di atas."
        }
    }
}