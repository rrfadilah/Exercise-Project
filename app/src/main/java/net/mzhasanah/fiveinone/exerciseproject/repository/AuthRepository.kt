package net.mzhasanah.fiveinone.exerciseproject.repository

import kotlinx.coroutines.flow.firstOrNull
import net.mzhasanah.fiveinone.exerciseproject.datastore.AuthDataStoreManager
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authDataStore: AuthDataStoreManager
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
}