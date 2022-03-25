package com.rizky.exercise_project.data

import com.rizky.exercise_project.R
import com.rizky.exercise_project.konsultasi.KonsultasiModel

object DummyKonsul {



    val dataKonsul: List<KonsultasiModel> = listOf(
        KonsultasiModel(
            ic_konsultasi = R.drawable.ic_dokter_umum,
            txt_konsultasi = "Saya butuh dokter umum"
        ),
        KonsultasiModel(
            ic_konsultasi = R.drawable.ic_psikiater,
            txt_konsultasi = "Saya butuh psikiater"
        ),
        KonsultasiModel(
            ic_konsultasi = R.drawable.ic_dokter_obat,
            txt_konsultasi = "Saya butuh dokter obat"
        ),
        KonsultasiModel(
            ic_konsultasi = R.drawable.ic_dokter_umum,
            txt_konsultasi = "Saya butuh dokter anak"
        ),
    )
}