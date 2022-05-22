package com.rizky.exercise_project.repository

import com.rizky.exercise_project.data.api.auth.*
import com.rizky.exercise_project.data.local.UserDAO
import com.rizky.exercise_project.data.local.UserEntity
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
    private val api: AuthAPI,
    private val dao: UserDAO
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

    suspend fun signUp(request: SignUpRequest): Response<SignUpResponse> {
        return api.signUp(request)
    }

    suspend fun logout(): Response<Unit> {
        val headers = mapOf(
            "user-token" to getToken().orEmpty()
        )
        return api.logout(headers)
    }

    suspend fun insertUser(userEntity: UserEntity): Long {
        return dao.insertUser(userEntity)
    }
}
