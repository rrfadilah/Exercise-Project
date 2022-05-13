package com.rizky.exercise_project.repository

import com.rizky.exercise_project.common.Resource
import com.rizky.exercise_project.common.Status
import com.rizky.exercise_project.data.api.home.HomeAPI
import com.rizky.exercise_project.data.api.home.HospitalResponse

class HomeRepository(private val homeAPI: HomeAPI) {
    suspend fun getHospital(): Resource<List<HospitalResponse>> {
        return Resource(
            status = Status.SUCCESS,
            data = homeAPI.getHospital().body(),
            message = null
        )
    }
}