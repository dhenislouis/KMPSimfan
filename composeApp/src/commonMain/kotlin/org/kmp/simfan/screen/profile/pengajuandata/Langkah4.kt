package org.kmp.simfan.screen.profile.pengajuandata

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.jetbrains.compose.resources.painterResource
import org.kmp.simfan.Routes
import org.kmp.simfan.core.Button1
import org.kmp.simfan.core.Label_Langkah
import org.kmp.simfan.model.IdentityStepRequest
import org.kmp.simfan.model.IndustrialSectorsData
import org.kmp.simfan.model.InvestmentObjectivesData
import org.kmp.simfan.model.JobData
import org.kmp.simfan.model.JobTitlesData
import org.kmp.simfan.model.MonthlySalariesData
import org.kmp.simfan.model.RevenueData
import org.kmp.simfan.presentation.auth.LoginViewModel
import org.kmp.simfan.presentation.profileSubmission.ProfileSubmissionViewModel
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.arrow_back
import simfan.composeapp.generated.resources.arrow_down

@Composable
fun Langkah4Screen(
    navController: NavController, currentRoute: Routes?,
    onBackClick: () -> Unit = {},
    onNext: () -> Unit = {}
) {
    val viewModel = remember { ProfileSubmissionViewModel() }

    val isLoading by viewModel.isLoading
    val errorMessage by viewModel.errorMessage
    val investmentObjectives by viewModel.investmentObjectives
    val revenues by viewModel.revenues
    val jobs by viewModel.jobs
    val jobTitles by viewModel.jobTitles
    val monthlySalaries by viewModel.monthlySalaries
    val industrialSectors by viewModel.industrialSectors


    var selectedObjective by remember { mutableStateOf<InvestmentObjectivesData?>(null) }
    var selectedRevenue by remember { mutableStateOf<RevenueData?>(null) }
    var selectedJob by remember { mutableStateOf<JobData?>(null) }
    var selectedJobTitle by remember { mutableStateOf<JobTitlesData?>(null) }
    var selectedSalary by remember { mutableStateOf<MonthlySalariesData?>(null) }
    var selectedSector by remember { mutableStateOf<IndustrialSectorsData?>(null) }
    val submitResult by viewModel.submitResult

    var workAddress by remember { mutableStateOf("") }
    var workPhone by remember { mutableStateOf("") }
    var motherName by remember { mutableStateOf("") }
//    var npwp by remember { mutableStateOf("") }
    val isFormValid by remember {
        derivedStateOf {
            selectedObjective != null &&
                    selectedRevenue != null &&
                    selectedJob != null &&
                    selectedJobTitle != null &&
                    selectedSalary != null &&
                    selectedSector != null &&
                    workAddress.isNotBlank() &&
                    workPhone.isNotBlank() &&
                    motherName.isNotBlank()
            // NPWP diasumsikan opsional, jadi tidak masuk validasi utama
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F4F4)) // bg_secondary
    ) {
        // 🔹 TopBar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    painter = painterResource(Res.drawable.arrow_back),
                    contentDescription = "Kembali",
                    tint = Color.Black,
                    modifier = Modifier.size(18.dp)
                )
            }

            Text(
                text = "Pengajuan Data",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.align(Alignment.Center)
            )

            Spacer(
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.CenterEnd)
            )
        }
        Box(modifier = Modifier.weight(1f)) {
            // 🔹 Konten scroll
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF4F4F4))
                        .padding(16.dp)
                ) {
                    Text(
                        "Langkah 4 dari 5",
                        fontSize = 11.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(Label_Langkah)
                            .padding(horizontal = 12.dp, vertical = 3.dp)
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        "Ceritakan Lebih Banyak Tentang Dirimu",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        "Isilah informasi berikut agar kami bisa mempersonalisasi layanan deposito untukmu.",
                        fontSize = 12.sp,
                        color = Color.Black
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(16.dp)
                ) {
                    DropdownField(
                        label = "Tujuan membuka rekening*",
                        hint = "Pilih kategori",
                        options = investmentObjectives.map { it.name },
                        selectedOption = selectedObjective?.name ?: "",
                        onOptionSelected = { selectedName ->
                            selectedObjective = investmentObjectives.find { it.name == selectedName }
                        }
                    )

                    DropdownField(
                        label = "Sumber Dana",
                        hint = "Pilih kategori",
                        options = revenues.map { it.name },
                        selectedOption = selectedRevenue?.name ?: "",
                        onOptionSelected = { selectedName ->
                            selectedRevenue = revenues.find { it.name == selectedName }
                        }
                    )

                    DropdownField(
                        label = "Pekerjaan",
                        hint = "Pilih kategori",
                        options = jobs.map { it.name },
                        selectedOption = selectedJob?.name ?: "",
                        onOptionSelected = { selectedName ->
                            selectedJob = jobs.find { it.name == selectedName }
                        }
                    )

                    DropdownField(
                        label = "Jabatan di pekerjaan",
                        hint = "Pilih kategori",
                        options = jobTitles.map { it.name },
                        selectedOption = selectedJobTitle?.name ?: "",
                        onOptionSelected = { selectedName ->
                            selectedJobTitle = jobTitles.find { it.name == selectedName }
                        }
                    )

                    DropdownField(
                        label = "Penghasilan Bulanan",
                        hint = "Pilih kategori",
                        options = monthlySalaries.map { it.range },
                        selectedOption = selectedSalary?.range ?: "",
                        onOptionSelected = { selectedName ->
                            selectedSalary = monthlySalaries.find { it.range == selectedName }
                        }
                    )

                    DropdownField(
                        label = "Sektor Industri",
                        hint = "Pilih kategori",
                        options = industrialSectors.map { it.name },
                        selectedOption = selectedSector?.name ?: "",
                        onOptionSelected = { selectedName ->
                            selectedSector = industrialSectors.find { it.name == selectedName }
                        }
                    )

                    InputField("Alamat Tempat Bekerja", "Masukkan alamat tempat kerja", workAddress, { workAddress = it })
                    InputField("Nomor Telepon Tempat Bekerja", "Masukkan Nomor Telepon Tempat Bekerja", workPhone, { workPhone = it }, KeyboardType.Phone)
                    InputField("Nama Ibu Kandung", "Masukkan nama ibu kandung", motherName, { motherName = it })
//                    InputField("NPWP", "Masukkan NPWP", npwp, { npwp = it }, KeyboardType.Number)
                }
            }
        }
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.White)
            }
        }

        // 7. Tampilkan pesan error jika ada
        errorMessage?.let { message ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Gagal memuat data:\n$message",
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(16.dp)
                        .background(Color.White, RoundedCornerShape(8.dp))
                        .padding(16.dp)
                )
            }
        }
        // 🔹 Bottom button
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp)
        ) {
            Button(
                onClick = {
                    val request = IdentityStepRequest(
                        investmentObjectiveId = selectedObjective!!.id,
                        revenueId = selectedRevenue!!.id,
                        jobId = selectedJob!!.id,
                        jobTitleId = selectedJobTitle!!.id,
                        monthlySalaryId = selectedSalary!!.id,
                        industrialSectorId = selectedSector!!.id,
                        workPhone = workPhone,
                        workAddress = workAddress,
                        motherMaidenName = motherName
                    )
                    // 2. Panggil fungsi di ViewModel dengan data yang sudah terkumpul
                    viewModel.submitIdentity(request)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                enabled = isFormValid && !isLoading,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Button1,
                    disabledContainerColor = Color.Gray
                ),
            ) {
                Text("Lanjut", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
            }
        }
        errorMessage?.let {
            Text("Error: $it", color = Color.Red)
        }
        submitResult?.let {
            onNext()
            Text("Sukses: $it", color = Color(0xFF0F9D58))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownField(
    label: String,
    hint: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Text(label, fontSize = 14.sp, color = Color(0xFF505559))
        Spacer(Modifier.height(4.dp))

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = selectedOption,
                onValueChange = {},
                readOnly = true, // Penting agar tidak bisa diketik
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .menuAnchor(),
                placeholder = { Text(hint, fontSize = 13.sp, color = Color(0xFF9CA3AF)) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                shape = RoundedCornerShape(8.dp),
                textStyle = LocalTextStyle.current.copy(fontSize = 13.sp),
                singleLine = true,
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption) },
                        onClick = {
                            onOptionSelected(selectionOption)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun InputField(
    label: String,
    hint: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Text(label, fontSize = 14.sp, color = Color(0xFF505559))
        Spacer(Modifier.height(4.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            placeholder = { Text(hint, fontSize = 13.sp, color = Color(0xFF9CA3AF)) },
            shape = RoundedCornerShape(8.dp),
            textStyle = LocalTextStyle.current.copy(fontSize = 13.sp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
        )
    }
}

//@Preview
//@Composable
//fun PreviewLangkah4UI() {
//    Langkah4UI()
//}
