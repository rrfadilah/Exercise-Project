package com.rizky.exercise_project.data

import com.rizky.exercise_project.R
import com.tegarpenemuan.minichallenge2.model.DoctorModel

object DummyDoctor {
    val Data_Doctor: List<DoctorModel> = listOf(
        DoctorModel(
            avatar = R.drawable.dokter1,
            name = "Alexa Rachel",
            desc = "Pediatrician",
        ),
        DoctorModel(
            avatar = R.drawable.dokter2,
            name = "Sunny Frank",
            desc = "Dentist",
        ),
        DoctorModel(
            avatar = R.drawable.dokter3,
            name = "Poe Minn",
            desc = "Podiatrist",
        )
    )
}