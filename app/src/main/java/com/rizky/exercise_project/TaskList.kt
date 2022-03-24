package com.rizky.exercise_project

object TaskList {
    val listDoctor = listOf<ModelDoctor>(
        ModelDoctor(image1 = R.drawable.icon_dokter_umum, text1 = "dokter umum"),
        ModelDoctor(image1 = R.drawable.icon_dokter_psikiater, text1 = "psikiater"),
        ModelDoctor(image1 = R.drawable.icon_dokter_obat, text1 = "dokter obat"),
        ModelDoctor(image1 = R.drawable.icon_dokter_psikiater, text1 = "dokter satu"),
        ModelDoctor(image1 = R.drawable.icon_dokter_umum, text1 = "dokter dua"),
        ModelDoctor(image1 = R.drawable.icon_dokter_obat, text1 = "dokter tiga"),
        ModelDoctor(image1 = R.drawable.icon_dokter_psikiater, text1 = "dokter empat"),
    )

    val listRatedDoctor = listOf<ModelDoctorSecond>(
        ModelDoctorSecond(image1 = R.drawable.image_doctor1, text1 = "Alexa Rachel", text2 = "Pediatrician"),
        ModelDoctorSecond(image1 = R.drawable.image_doctor3, text1 = "Sunny Frank", text2 = "Dentist"),
        ModelDoctorSecond(image1 = R.drawable.image_doctor2, text1 = "Poe Minn", text2 = "Poadiatrist"),
    )

    val listGoodNews = listOf<ModelDoctorThird>(
        ModelDoctorThird(image1 = R.drawable.goodnews1, text1 = "Is it safe to stay at home during coronavirus?", text2 = "Today"),
        ModelDoctorThird(image1 = R.drawable.goodnews2, text1 = "Consume yellow citrus helps you healthier", text2 = "Yesterday"),
        ModelDoctorThird(image1 = R.drawable.goodnews3, text1 = "Learn how to make a proper orange juice at home", text2 = "2 days ago"),
    )

    val listDoctorAnak = listOf<ModelMessages>(
        ModelMessages(image1 = R.drawable.image_doctor1, text1 = "Alexander Jannie", text2 = "Baik ibu, terima kasih banyak atas wakt..."),
        ModelMessages(image1 = R.drawable.image_doctor1, text1 = "John McParker Steve", text2 = "Oke menurut pak dokter bagaimana unt..."),
        ModelMessages(image1 = R.drawable.image_doctor1, text1 = "Nairobi Putri Hayza", text2 = "oh tentu saja tidak karena jeruk it...")
    )

    val listHospitals = listOf<ModelHospital>(
        ModelHospital(image1 = R.drawable.goodnews1, text1 = "Is it safe to stay at home during coronavirus?", text2 = "Today"),
        ModelHospital(image1 = R.drawable.goodnews2, text1 = "Consume yellow citrus helps you healthier", text2 = "Yesterday"),
        ModelHospital(image1 = R.drawable.goodnews3, text1 = "Learn how to make a proper orange juice at home", text2 = "2 days ago"),
    )
}