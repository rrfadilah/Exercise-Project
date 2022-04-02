package com.rizky.exercise_project

import com.rizky.exercise_project.Doctor.ModelDoctor
import com.rizky.exercise_project.Doctor.ModelDoctorSecond
import com.rizky.exercise_project.Doctor.ModelDoctorThird
import com.rizky.exercise_project.facebook.ui.home_facebook.ReelsFacebookModel
import com.rizky.exercise_project.LinkedIn.Model.ModelLinkedIn

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
    val listReelsFacebook = listOf<ReelsFacebookModel>(
        ReelsFacebookModel(image1 = R.drawable.rectangle483, image2 = R.drawable.profilephoto ),
        ReelsFacebookModel(image1 = R.drawable.rectangle483, image2 = R.drawable.profilephoto)
    )

    val listFeeds = listOf<ModelLinkedIn>(
        ModelLinkedIn(
            image1 = R.drawable.fiverr,
            text1 = "Fiverr",
            text2 = "321,725 followers",
            text3 = "1d",
            text4 = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley",
            image2 = R.drawable.img_feed1,
            text5 = "12,742",
            text6 = "1,024 comments",
            text7 = "625 shares"
        ),
        ModelLinkedIn(
            image1 = R.drawable.lottie2,
            text1 = "Lottie",
            text2 = "786,122 followers",
            text3 = "1d",
            text4 = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley",
            image2 = R.drawable.img_feed6,
            text5 = "24,346",
            text6 = "547 comments",
            text7 = "21 shares"
        ),
        ModelLinkedIn(
            image1 = R.drawable.figma,
            text1 = "Figma",
            text2 = "987,468 followers",
            text3 = "2d",
            text4 = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley",
            image2 = R.drawable.img_feed3,
            text5 = "5,879",
            text6 = "6,341 comments",
            text7 = "867 shares"
        ),
        ModelLinkedIn(
            image1 = R.drawable.free3dicon,
            text1 = "Free3dIcon",
            text2 = "12,456 followers",
            text3 = "2d",
            text4 = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley",
            image2 = R.drawable.img_feed4,
            text5 = "4,567",
            text6 = "124 comments",
            text7 = "21 shares"
        ),
        ModelLinkedIn(
            image1 = R.drawable.figma,
            text1 = "Figma",
            text2 = "856,325 followers",
            text3 = "3d",
            text4 = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley",
            image2 = R.drawable.img_feed5,
            text5 = "64,231",
            text6 = "1,254 comments",
            text7 = "984 shares"
        ),
    )

}