package net.mzhasanah.fiveinone.exerciseproject.common

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStoreCounter: DataStore<Preferences> by preferencesDataStore(name = "counter")