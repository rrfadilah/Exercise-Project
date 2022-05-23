package net.mzhasanah.fiveinone.exerciseproject.repository

import net.mzhasanah.fiveinone.exerciseproject.common.Resource
import net.mzhasanah.fiveinone.exerciseproject.common.Status
import net.mzhasanah.fiveinone.exerciseproject.data.api.home.HomeAPI
import net.mzhasanah.fiveinone.exerciseproject.data.api.home.HospitalResponse

class HomeRepository(private val homeAPI: HomeAPI) {
    suspend fun getHospital(): Resource<List<HospitalResponse>> {
        return Resource(
            status = Status.SUCCESS,
            data = homeAPI.getHospital().body(),
            message = null
        )
    }
}