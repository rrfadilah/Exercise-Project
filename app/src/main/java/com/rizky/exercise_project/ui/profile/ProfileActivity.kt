package com.rizky.exercise_project.ui.profile

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.bumptech.glide.Glide
import com.rizky.exercise_project.Constant
import com.rizky.exercise_project.common.dataStoreCounter
import com.rizky.exercise_project.database.MyDoctorDatabase
import com.rizky.exercise_project.databinding.ActivityProfileBinding
import com.rizky.exercise_project.datastore.CounterDataStoreManager
import com.rizky.exercise_project.network.ImageApiClient
import com.rizky.exercise_project.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private val viewModel: ProfileViewModel by viewModels {
        ProfileViewModel.Factory(
            ProfileRepository(
                imageAPI = ImageApiClient.instanceImage,
                db = MyDoctorDatabase.getInstance(this),
                prefDataStore = CounterDataStoreManager(this)
            )
        )
    }
    private val progressDialog: ProgressDialog by lazy { ProgressDialog(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindView()
        bindViewModel()

        viewModel.getProfile()
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

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }

        binding.btnIncrement.setOnClickListener {
            viewModel.increment()
        }

        binding.btnDecrement.setOnClickListener {
            viewModel.decrement()
        }
    }

    private fun bindViewModel() {
        viewModel.shouldShowImage.observe(this) {
            Glide.with(this)
                .load(it)
                .circleCrop()
                .into(binding.ivProfile)
        }

        viewModel.shouldShowProfile.observe(this) {
            binding.tvName.text = it.name
            binding.tvJob.text = it.job

            Glide.with(this)
                .load(it.image)
                .circleCrop()
                .into(binding.ivProfile)
        }

        viewModel.shouldShowLoading.observe(this) {
            if (it) {
                progressDialog.setMessage("Loading...")
                progressDialog.show()
            } else {
                progressDialog.hide()
            }
        }

        viewModel.shouldShowCounter.observe(this) {
            binding.tvCounter.text = "$it"
        }
    }
}