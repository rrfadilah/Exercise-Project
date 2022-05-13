package com.rizky.exercise_project.menu.ui.profile

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.rizky.exercise_project.databinding.ActivityProfileBinding
import com.rizky.exercise_project.network.ImageApiClient
import com.rizky.exercise_project.repository.ProfileRepository
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private val viewModel: ProfileViewModel by viewModels {
        ProfileViewModel.Factory(ProfileRepository(ImageApiClient.instanceImage))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindView()
        bindViewModel()
    }

    private fun bindView() {
        val getContent =
            registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
                uri?.let {
                    // image/png or jpeg or gif
                    val type = contentResolver.getType(it)
                    // temp-712793019827391820739.tmp < nano time, directory
                    // akan terbuat secara otomatis kalau value nya null,> akan di simpan dalam dir cache
                    val tempFile = File.createTempFile("temp-", null, null)
                    val inputstream = contentResolver.openInputStream(uri)

                    tempFile.outputStream().use {
                        inputstream?.copyTo(it)
                    }

                    val requestBody: RequestBody = tempFile.asRequestBody(type?.toMediaType())
                    val body =
                        MultipartBody.Part.createFormData("image", tempFile.name, requestBody)

                    viewModel.uploadImage(body)
                }
            }

        binding.ivProfile.setOnClickListener {
            getContent.launch("image/*")
        }
    }

    private fun bindViewModel() {
        viewModel.shouldShowImage.observe(this) {
            Glide.with(this)
                .load(it)
                .circleCrop()
                .into(binding.ivProfile)
        }
    }
}