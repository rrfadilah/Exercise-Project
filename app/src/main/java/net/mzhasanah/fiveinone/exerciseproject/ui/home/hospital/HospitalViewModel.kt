package net.mzhasanah.fiveinone.exerciseproject.ui.home.hospital

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.mzhasanah.fiveinone.exerciseproject.common.Status
import net.mzhasanah.fiveinone.exerciseproject.model.HospitalModel
import net.mzhasanah.fiveinone.exerciseproject.repository.HomeRepository

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