package com.rizky.exercise_project.repository

import com.rizky.exercise_project.datastore.AuthDataStoreManager
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

/**
 * com.rizky.exercise_project.repository
 *
 * Created by Rizky Fadilah on 12/05/22.
 * https://github.com/rizkyfadilah
 *
 */

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
