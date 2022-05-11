package com.rizky.exercise_project.common

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

/**
 * com.rizky.exercise_project.common
 *
 * Created by Rizky Fadilah on 11/05/22.
 * https://github.com/rizkyfadilah
 *
 */

val Context.dataStoreCounter: DataStore<Preferences> by preferencesDataStore(name = "counter")