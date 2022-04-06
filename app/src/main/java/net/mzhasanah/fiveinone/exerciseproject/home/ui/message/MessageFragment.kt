package net.mzhasanah.fiveinone.exerciseproject.home.ui.message

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.recyclerview.widget.RecyclerView
import net.mzhasanah.fiveinone.exerciseproject.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MessageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MessageFragment : Fragment() {
//    @RequiresApi(Build.VERSION_CODES.M)
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message, container, false)
//        val recyclerView: RecyclerView? = view?.findViewById(R.id.rv_message)
//
//        // untuk keperluan datanya
//        val messages: List<MessageModel> = listOf(
//            MessageModel(
//                image = "https://i.ibb.co/zJHYGBP/binarlogo.jpg",
//                name = "Muhammad Zain Ramadhan",
//                lastMessage = "Hallo gais semuanya disini"
//            ),
//            MessageModel(
//                image = "https://lh3.googleusercontent.com/-nRrSWU--Dfk/X2LaaEbsIyI/AAAAAAAAAQA/n7AH_xi8iAI9WT7aslf0YGAt4Ab_lElvwCLcBGAsYHQ/s1600/1600313957610819-0.png",
//                name = "Aliza Farikhah",
//                lastMessage = "Mau isi aapa disini boleh aja ya teman teman semuanya",
//            ),
//            MessageModel(
//                image = "https://i.pinimg.com/originals/e9/71/25/e9712514c7cd98b232b27f444a504b3e.jpg",
//                name = "Mau'idzoh Hasanah",
//                lastMessage = "Halo bayar hutang jangan lupa!!"
//            ),
//            MessageModel(
//                image = "https://images.bisnis-cdn.com/thumb/posts/2019/10/13/1158440/langit-jepang.jpg?w=400&h=400",
//                name = "Bella Shafira",
//                lastMessage = "Assalamu'alaikum, hari ini ada jadwal kosong?",
//            ),
//            MessageModel(
//                image = "https://img.tek.id/crop/600x400/content/2021/05/21/40991/nickelodeon-akan-garap-film-spin-off-live-action-sandy-cheeks-9oWmTe67O2.jpg",
//                name = "Sandy Chick",
//                lastMessage = "Telah meninggal dunia bapak dari saudara saya",
//            ),
//            MessageModel(
//                image = "https://pbs.twimg.com/profile_images/1260483394871472133/QyNUYyHM_400x400.jpg",
//                name = "Rudy Tabuty",
//                lastMessage = "Hei dimana kapur baru aku kamu simpan?",
//            )
//        )
//
//        // untuk keperluan data ke recyclerviewnya
//        val adapter = MessageAdapter(messages)
//        recyclerView?.adapter = adapter
//
//        val permissionsCheck = checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)
//        if (permissionsCheck == PackageManager.PERMISSION_GRANTED){
//            Toast.makeText(this, "Access Denied", Toast.LENGTH_LONG).show()
//            getLatLong()
//        } else {
//            Toast.makeText(this, "Access Denied", Toast.LENGTH_LONG).show()
//            requestPermissionLocation()
//        }
    }

//    @RequiresApi(Build.VERSION_CODES.M)
//    fun requestPermissionLocation(){
//        requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 1001)
//    }
//
//    @SuppressLint("MissingPermission")
//    fun getLatLong(){
//        val locationManager = applicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        val location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
//        Toast.makeText(this, "Our Location id ${location?.latitude}, ${location?.longitude}", Toast.LENGTH_LONG).show()
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        when(requestCode) {
//            1001 -> {
//                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(this, "User Chooses Access Granted", Toast.LENGTH_LONG).show()
//                    getLatLong()
//                } else {
//                    Toast.makeText(this, "User Choose Access Denied", Toast.LENGTH_LONG).show()
//                }
//            }
//            else -> {
//                Toast.makeText(this, "Wrong requested", Toast.LENGTH_LONG).show()
//            }
//        }
//    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MessageFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MessageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}