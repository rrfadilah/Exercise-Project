package net.mzhasanah.fiveinone.exerciseproject.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import net.mzhasanah.fiveinone.exerciseproject.Constant
import javax.inject.Inject

class AuthDataStoreManager @Inject constructor(private val context: Context) {
    companion object {
        val Context.dataStoreAuth: DataStore<Preferences> by preferencesDataStore(
            name = Constant.PrefDataStore.PREF_NAME,
            produceMigrations = ::sharedPreferencesMigration
        )

        private fun sharedPreferencesMigration(context: Context) =
            listOf(SharedPreferencesMigration(context, Constant.Preferences.PREF_NAME))
    }

    fun getToken(): Flow<String> {
        return context.dataStoreAuth.data.map { preferences ->
            preferences[Constant.PrefDataStore.TOKEN_KEY].orEmpty()
        }
    }

    suspend fun setToken(value: String) {
        context.dataStoreAuth.edit { preferences ->
            preferences[Constant.PrefDataStore.TOKEN_KEY] = value
        }
    }
}