package com.rizky.exercise_project.ui.home.hospital

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rizky.exercise_project.common.Status
import com.rizky.exercise_project.model.HospitalModel
import com.rizky.exercise_project.repository.HomeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * com.rizky.exercise_project.ui.home.hospital
 *
 * Created by Rizky Fadilah on 30/04/22.
 * https://github.com/rizkyfadilah
 *
 */

class HospitalViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    val shouldShowData: MutableLiveData<List<HospitalModel>> = MutableLiveData()
    val shouldShowLoading: MutableLiveData<Boolean> = MutableLiveData()
    val shouldShowError: MutableLiveData<String> = MutableLiveData()

    fun onViewLoaded() {
        getHospital()
    }

    private fun getHospital() {
        CoroutineScope(Dispatchers.IO).launch {
            val result = homeRepository.getHospital()
            withContext(Dispatchers.Main) {
                when (result.status) {
                    Status.SUCCESS -> {
                        shouldShowData.postValue(
                            result.data?.map {
                                HospitalModel(
                                    id = it.objectId.orEmpty(),
                                    title = it.title.orEmpty(),
                                    address = it.address.orEmpty(),
                                    image = it.image.orEmpty()
                                )
                            } ?: emptyList()
                        )
                    }
                    Status.LOADING -> {
                        shouldShowLoading.postValue(result.data.isNullOrEmpty())
                    }
                    Status.ERROR -> {
                        shouldShowError.postValue(result.message.orEmpty())
                    }
                }
            }
        }
    }

    class Factory(private val homeRepository: HomeRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HospitalViewModel::class.java)) {
                return HospitalViewModel(homeRepository) as T
            }
            throw IllegalArgumentException("Unknown class name")
        }
    }
}