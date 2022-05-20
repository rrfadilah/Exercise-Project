package com.rizky.exercise_project.repository

import com.rizky.exercise_project.data.api.auth.AuthAPI
import com.rizky.exercise_project.data.api.auth.SignInRequest
import com.rizky.exercise_project.data.api.auth.SignInResponse
import com.rizky.exercise_project.datastore.AuthDataStoreManager
import kotlinx.coroutines.flow.firstOrNull
import retrofit2.Response
import javax.inject.Inject

/**
 * com.rizky.exercise_project.repository
 *
 * Created by Rizky Fadilah on 12/05/22.
 * https://github.com/rizkyfadilah
 *
 */

class AuthRepository @Inject constructor(
    private val authDataStore: AuthDataStoreManager,
    private val api: AuthAPI
) {
    suspend fun clearToken() {
        return updateToken("")
    }

    suspend fun updateToken(value: String) {
        authDataStore.setToken(value)
    }

    suspend fun getToken(): String? {
        return authDataStore.getToken().firstOrNull()
    }

    suspend fun signIn(request: SignInRequest): Response<SignInResponse> {
        return api.signIn(request)
    }
}
