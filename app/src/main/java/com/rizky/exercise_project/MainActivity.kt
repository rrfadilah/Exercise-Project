package com.rizky.exercise_project

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.rizky.exercise_project.menu.ui.hospital.HospitalAdapter
import com.rizky.exercise_project.menu.ui.hospital.HospitalModel
import com.rizky.exercise_project.menu.ui.message.MessageAdapter
import com.rizky.exercise_project.menu.ui.message.MessageModel
import com.rizky.exercise_project.menu.ui.message.data.dummy.messages

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvHospital: RecyclerView = findViewById(R.id.rvHospital)
        val hospital: List<HospitalModel> = listOf(
            HospitalModel(
                avatar = "https://cdn0-production-images-kly.akamaized.net/VbMUIZaH7KtXcN9hqx9-48dmb5Q=/640x360/smart/filters:quality(75):strip_icc():format(jpeg)/kly-media-production/medias/3077866/original/005810600_1584354134-IMG20200316141111.jpg",
                name = "RSUD Dr. Saiful Anwar",
                desc = "Malang",
            ),
            HospitalModel(
                avatar = "https://static.gatra.com/foldershared/images/2020/farid/10-Oct/IMG_0543.jpg",
                name = "Rumah Sakit Mitra Siaga",
                desc = "Tegal",
            ), HospitalModel(
                avatar = "https://2.bp.blogspot.com/-PHZUHmNQK4M/VR8rWD-pNeI/AAAAAAAAgs4/UAbeB8qB1W8/w1200-h630-p-k-no-nu/rs-phc-sby.jpg",
                name = "Rumah Sakit PHC",
                desc = "Surabaya",
            ), HospitalModel(
                avatar = "https://d1ojs48v3n42tp.cloudfront.net/provider_location_list/116536_17-2-2020_20-57-42.jpg",
                name = "RS Bhayangkara",
                desc = "Kediri",
            )
        )

        // untuk keperluan data ke recyclerviewnya
        val adapHospital = HospitalAdapter(hospital)
        rvHospital.adapter = adapHospital

        //val recyclerView: RecyclerView = findViewById(R.id.rv_message)

//        val adapter = MessageAdapter(messages)
//        recyclerView.adapter = adapter
    }
}