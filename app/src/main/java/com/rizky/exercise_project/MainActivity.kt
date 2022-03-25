package com.rizky.exercise_project

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.rizky.exercise_project.menu.ui.hospital.HospitalAdapter
import com.rizky.exercise_project.menu.ui.hospital.HospitalModel

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvHospital: RecyclerView = findViewById(R.id.rvHospital)
        val hospital: List<HospitalModel> = listOf(
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

        // untuk keperluan data ke recyclerviewnya
        val adapHospital = HospitalAdapter(hospital)
        rvHospital.adapter = adapHospital

        //val recyclerView: RecyclerView = findViewById(R.id.rv_message)

//        val adapter = MessageAdapter(messages)
//        recyclerView.adapter = adapter
    }
}