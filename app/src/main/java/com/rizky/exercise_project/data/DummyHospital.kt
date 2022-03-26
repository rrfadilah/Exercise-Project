package com.rizky.exercise_project.data

import com.rizky.exercise_project.R
import com.rizky.exercise_project.menu.ui.hospital.HospitalModel

object DummyHospital {
    val dataHospital: List<HospitalModel> = listOf(
        HospitalModel(
            avatar = R.drawable.hospitalbg,
            name = "RSUD Dr. Saiful Anwar",
            desc = "Malang",
        ),
        HospitalModel(
            avatar = R.drawable.hospitalbg,
            name = "Rumah Sakit Mitra Siaga",
            desc = "Tegal",
        ), HospitalModel(
            avatar = R.drawable.hospitalbg,
            name = "Rumah Sakit PHC",
            desc = "Surabaya",
        ), HospitalModel(
            avatar = R.drawable.hospitalbg,
            name = "RS Bhayangkara",
            desc = "Kediri",
        )
    )
}