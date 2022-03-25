package com.rizky.exercise_project.data

import com.rizky.exercise_project.R
import com.rizky.exercise_project.konsultasi.KonsultasiModel

object DummyKonsul {
    val dataKonsul: List<KonsultasiModel> = listOf(
        KonsultasiModel(
            ic_konsultasi = R.drawable.img_dokter_umum,
            txt_konsultasi = "Hello"
        ),
        KonsultasiModel(
            ic_konsultasi = R.drawable.img_dokter_umum,
            txt_konsultasi = "Hello 2"
        ),
    )
}