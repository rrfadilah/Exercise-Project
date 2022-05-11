package com.rizky.exercise_project.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.rizky.exercise_project.Constant
import com.rizky.exercise_project.common.dataStoreCounter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * com.rizky.exercise_project.datastore
 *
 * Created by Rizky Fadilah on 11/05/22.
 * https://github.com/rizkyfadilah
 *
 */

class CounterDataStoreManager(private val context: Context) {

    // Fungsi ini akan memetakan Flow<Preferences> menjadi Flow<Int>
    fun getCounter(): Flow<Int> {
        return context.dataStoreCounter.data.map { preferences ->
            preferences[Constant.PrefDataStore.COUNTER_KEY] ?: 0
        }
    }
    // Fungsi ini akan memetakan Flow<Preferences> menjadi Flow<Int>
    suspend fun setCounter(value: Int) {
        context.dataStoreCounter.edit { preferences ->
            preferences[Constant.PrefDataStore.COUNTER_KEY] = value
        }
    }

    suspend fun incrementCounter() {
        context.dataStoreCounter.edit { preferences->
            // Membaca value saat ini dari preferences
            val currentCounterValue = preferences[Constant.PrefDataStore.COUNTER_KEY] ?: 0
            // Menulis value saat ini dan ditambahkan 1 ke dalam preferences
            preferences[Constant.PrefDataStore.COUNTER_KEY] = currentCounterValue + 1
        }
    }

    suspend fun decrementCounter() {
        context.dataStoreCounter.edit { preferences->
            // Membaca value saat ini dari preferences
            val currentCounterValue = preferences[Constant.PrefDataStore.COUNTER_KEY] ?: 0
            // Menulis value saat ini dan ditambahkan 1 ke dalam preferences
            preferences[Constant.PrefDataStore.COUNTER_KEY] = currentCounterValue - 1
        }
    }
}