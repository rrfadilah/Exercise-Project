package com.rizky.exercise_project

import com.rizky.exercise_project.Doctor.ModelDoctor
import com.rizky.exercise_project.Doctor.ModelDoctorSecond
import com.rizky.exercise_project.Doctor.ModelDoctorThird

object TaskList {
    val listDoctor = listOf<ModelDoctor>(
        ModelDoctor(image1 = R.drawable.ic_icon_butuh_dokter_umum_1, text1 = "dokter umum"),
        ModelDoctor(image1 = R.drawable.ic_icon_butuh_dokter_umum_1, text1 = "psikiater"),
        ModelDoctor(image1 = R.drawable.ic_icon_butuh_dokter_umum_1, text1 = "dokter obat"),
        ModelDoctor(image1 = R.drawable.ic_icon_butuh_dokter_umum_1, text1 = "dokter satu"),
        ModelDoctor(image1 = R.drawable.ic_icon_butuh_dokter_umum_1, text1 = "dokter dua"),
        ModelDoctor(image1 = R.drawable.ic_icon_butuh_dokter_umum_1, text1 = "dokter tiga"),
        ModelDoctor(image1 = R.drawable.ic_icon_butuh_dokter_umum_1, text1 = "dokter empat"),
    )

    val listRatedDoctor = listOf<ModelDoctorSecond>(
        ModelDoctorSecond(image1 = R.drawable.ic_icon_butuh_dokter_umum_1, text1 = "Alexa Rachel", text2 = "Pediatrician"),
        ModelDoctorSecond(image1 = R.drawable.ic_icon_butuh_dokter_umum_1, text1 = "Sunny Frank", text2 = "Dentist"),
        ModelDoctorSecond(image1 = R.drawable.ic_icon_butuh_dokter_umum_1, text1 = "Poe Minn", text2 = "Poadiatrist"),
    )

    val listGoodNews = listOf<ModelDoctorThird>(
        ModelDoctorThird(image1 = R.drawable.ic_icon_butuh_dokter_umum_1, text1 = "Is it safe to stay at home during coronavirus?", text2 = "Today"),
        ModelDoctorThird(image1 = R.drawable.ic_icon_butuh_dokter_umum_1, text1 = "Consume yellow citrus helps you healthier", text2 = "Yesterday"),
        ModelDoctorThird(image1 = R.drawable.ic_icon_butuh_dokter_umum_1, text1 = "Learn how to make a proper orange juice at home", text2 = "2 days ago"),
    )
}