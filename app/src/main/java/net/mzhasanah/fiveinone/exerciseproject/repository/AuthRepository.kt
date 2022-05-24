package net.mzhasanah.fiveinone.exerciseproject.repository

import kotlinx.coroutines.flow.firstOrNull
import net.mzhasanah.fiveinone.exerciseproject.data.api.auth.*
import net.mzhasanah.fiveinone.exerciseproject.data.local.UserDAO
import net.mzhasanah.fiveinone.exerciseproject.data.local.UserEntity
import net.mzhasanah.fiveinone.exerciseproject.datastore.AuthDataStoreManager
import retrofit2.Response
import javax.inject.Inject

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