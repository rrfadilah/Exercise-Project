package net.mzhasanah.fiveinone.exerciseproject.repository

import kotlinx.coroutines.flow.firstOrNull
import net.mzhasanah.fiveinone.exerciseproject.data.api.auth.AuthAPI
import net.mzhasanah.fiveinone.exerciseproject.data.api.auth.SignInRequest
import net.mzhasanah.fiveinone.exerciseproject.data.api.auth.SignInResponse
import net.mzhasanah.fiveinone.exerciseproject.datastore.AuthDataStoreManager
import retrofit2.Response
import javax.inject.Inject

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