package com.learn.smartabsensi.features.presentation.pages.sub_pages

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.learn.smartabsensi.R
import com.learn.smartabsensi.core.themes.Background
import com.learn.smartabsensi.core.themes.DarkIndigo
import com.learn.smartabsensi.core.themes.Indigo
import com.learn.smartabsensi.core.themes.IndigoLigth
import com.learn.smartabsensi.core.themes.IndigoSoft
import com.learn.smartabsensi.core.themes.TextPrimary
import com.learn.smartabsensi.core.themes.TextSecondary
import com.learn.smartabsensi.features.data.models.UserModel
import com.learn.smartabsensi.features.presentation.components.Gender
import com.learn.smartabsensi.features.presentation.components.MyTextField
import com.learn.smartabsensi.features.presentation.components.Selection
import com.learn.smartabsensi.features.presentation.components.change_profile.LargeTextField
import com.learn.smartabsensi.features.presentation.components.change_profile.TopBarChangeProfile
import com.learn.smartabsensi.features.presentation.view_models.ChangeProfileViewModel

@Composable
fun ChangeProfilePage(
    user: UserModel,
    cvm: ChangeProfileViewModel = viewModel(),
    previousScreen: () -> Unit
) {
    val fullname by cvm.fullName.collectAsStateWithLifecycle()
    val email by cvm.email.collectAsStateWithLifecycle()
    val numberphone by cvm.numberphone.collectAsStateWithLifecycle()
    val nisn by cvm.nisn.collectAsStateWithLifecycle()
    val classs by cvm.classs.collectAsStateWithLifecycle()
    val major by cvm.major.collectAsStateWithLifecycle()
    val religion by cvm.religion.collectAsStateWithLifecycle()
    val address by cvm.address.collectAsStateWithLifecycle()
    val biodata by cvm.biodata.collectAsStateWithLifecycle()
    val uiState by cvm.uiState.collectAsStateWithLifecycle()

    var fullNameIsNotRequired by remember { mutableStateOf(false) }
    var fullNameIsNotRequiredMess by remember { mutableStateOf("") }

    var emailIsNotRequired by remember { mutableStateOf(false) }
    var emailIsNotRequiredMess by remember { mutableStateOf("") }

    var numberphoneIsNotRequired by remember { mutableStateOf(false) }
    var numberphoneIsNotRequiredMess by remember { mutableStateOf("") }

    var nisnIsNotRequired by remember { mutableStateOf(false) }
    var nisnIsNotRequiredMess by remember { mutableStateOf("") }

    var classIsNotRequired by remember { mutableStateOf(false) }
    var classIsNotRequiredMess by remember { mutableStateOf("") }

    var majorIsNotRequired by remember { mutableStateOf(false) }
    var majorIsNotRequiredMess by remember { mutableStateOf("") }

    var religionIsNotRequired by remember { mutableStateOf(false) }
    var religionIsNotRequiredMess by remember { mutableStateOf("") }

    var addressIsNotRequired by remember { mutableStateOf(false) }
    var addressIsNotRequiredMess by remember { mutableStateOf("") }

    var biodataIsNotRequired by remember { mutableStateOf(false) }
    var biodataIsNotRequiredMess by remember { mutableStateOf("") }

    val optionClass = listOf(
        "X",
        "XII",
        "XIII"
    )
    val optionMajor = listOf(
        "A",
        "B",
        "C",
        "D",
        "E",
        "F",
        "G"
    )

    LaunchedEffect(key1 = user) {
        cvm.fullNameChanged(user.name)
        cvm.emailChanged(user.email)
        cvm.nisnChanged(user.nisn)
        cvm.classChanged(user.classRoom)
        cvm.majorChanged(user.className)
        cvm.genderChanged(user.gender)
        cvm.addressChanged(user.address)
        cvm.biodataChanged(user.biodata)
    }

    Scaffold(
        topBar = {
            TopBarChangeProfile(
                uiState = uiState,
                previousScreen = previousScreen,
                onSaveClick = {
                    cvm.updateProfile {
                        previousScreen()
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(Background)
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .background(IndigoLigth),
                contentAlignment = Alignment.BottomCenter
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(Background)
                )
                AsyncImage(
                    model = user.photoUrl,
                    contentDescription = "photo profil",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .shadow(
                            elevation = 8.dp,
                            clip = false,
                            shape = CircleShape,
                            ambientColor = DarkIndigo,
                            spotColor = DarkIndigo.copy(alpha = 0.8f)
                        )
                        .clip(CircleShape)
                        .border(
                            width = 2.dp,
                            color = Indigo,
                            shape = CircleShape
                        )
                )
            }
            Spacer(Modifier.height(40.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Background)
                    .padding(horizontal = 18.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_user),
                        contentDescription = null,
                        tint = Indigo,
                        modifier = Modifier
                            .background(
                                color = IndigoSoft,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(6.dp)
                    )
                    Text(
                        text = "Informasi Dasar",
                        fontWeight = FontWeight.ExtraBold,
                        color = TextPrimary,
                        fontSize = 18.sp
                    )
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(1.5.dp)
                            .background(TextSecondary.copy(alpha = 0.1f))
                    )
                }
                MyTextField(
                    icon = R.drawable.ic_user_thin,
                    tabel = "NAMA LENGKAP *",
                    value = fullname,
                    defaultInnerTextField = "Nama lengkap",
                    isNotMeetRequired = fullNameIsNotRequired
                ) {
                    cvm.fullNameChanged(it)
                }
                MyTextField(
                    icon = R.drawable.ic_email,
                    tabel = "EMAIL",
                    value = email,
                    defaultInnerTextField = "example@gmail.com",
                    isNotMeetRequired = emailIsNotRequired
                ) {
                    cvm.emailChanged(it)
                }
                MyTextField(
                    icon = R.drawable.ic_call,
                    tabel = "NOMOR TELEPON",
                    value = numberphone,
                    defaultInnerTextField = "08*********",
                    isNotMeetRequired = numberphoneIsNotRequired
                ) {
                    cvm.numberphoneChanged(it)
                }
                MyTextField(
                    icon = R.drawable.ic_user_thin,
                    tabel = "NISN",
                    value = nisn,
                    defaultInnerTextField = "123456",
                    isNotMeetRequired = nisnIsNotRequired
                ) {
                    cvm.nisnChanged(it)
                }
                Spacer(Modifier.height(18.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_dispen),
                        contentDescription = null,
                        tint = Indigo,
                        modifier = Modifier
                            .background(
                                color = IndigoSoft,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(6.dp)
                    )
                    Text(
                        text = "Data Akademik",
                        fontWeight = FontWeight.ExtraBold,
                        color = TextPrimary,
                        fontSize = 18.sp
                    )
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(1.5.dp)
                            .background(TextSecondary.copy(alpha = 0.1f))
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Selection(
                        modifier = Modifier.weight(1f),
                        fieldName = "Kelas",
                        selectionOption = optionClass,
                        selectedOption = classs,
                        isNotMeetRequirement = classIsNotRequired
                    ) {
                        cvm.classChanged(it)
                    }
                    Selection(
                        modifier = Modifier.weight(1f),
                        fieldName = "Sub-kelas",
                        selectionOption = optionMajor,
                        selectedOption = major,
                        isNotMeetRequirement = majorIsNotRequired
                    ) {
                        cvm.majorChanged(it)
                    }
                }
                Spacer(Modifier.height(18.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_user),
                        contentDescription = null,
                        tint = Indigo,
                        modifier = Modifier
                            .background(
                                color = IndigoSoft,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(6.dp)
                    )
                    Text(
                        text = "Informasi Personal",
                        fontWeight = FontWeight.ExtraBold,
                        color = TextPrimary,
                        fontSize = 18.sp
                    )
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(1.5.dp)
                            .background(TextSecondary.copy(alpha = 0.1f))
                    )
                }
                Gender(
                    initialGender = user.gender
                ) {
                    cvm.genderChanged(it)
                }
                MyTextField(
                    icon = R.drawable.ic_star,
                    tabel = "AGAMA",
                    value = religion,
                    defaultInnerTextField = "Agama mu",
                    isNotMeetRequired = religionIsNotRequired
                ) {
                    cvm.religionChanged(it)
                }
                Spacer(Modifier.height(18.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_location),
                        contentDescription = null,
                        tint = Indigo,
                        modifier = Modifier
                            .background(
                                color = IndigoSoft,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(6.dp)
                    )
                    Text(
                        text = "Kontak & Lainnya",
                        fontWeight = FontWeight.ExtraBold,
                        color = TextPrimary,
                        fontSize = 18.sp
                    )
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(1.5.dp)
                            .background(TextSecondary.copy(alpha = 0.1f))
                    )
                }

                LargeTextField(
                    user = user,
                    cvm = cvm,
                    value = address,
                    tabel = "ALAMAT LENGKAP",
                    innerTextFieldd = "Jl. Imam Sukari No. 86, Jawa Timur"
                ) {
                    cvm.addressChanged(it)
                }
                LargeTextField(
                    user = user,
                    cvm = cvm,
                    value = biodata,
                    tabel = "BIO SINGKAT (opsional)",
                    innerTextFieldd = "Ceritakan sedikit tentangmu..."
                ) {
                    cvm.biodataChanged(it)
                }
            }
        }
    }
}