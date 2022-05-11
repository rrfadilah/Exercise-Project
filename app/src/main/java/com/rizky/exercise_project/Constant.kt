package com.rizky.exercise_project

import androidx.datastore.preferences.core.intPreferencesKey

/**
 * com.rizky.exercise_project
 *
 * Created by Rizky Fadilah on 21/03/22.
 * https://github.com/rizkyfadilah
 *
 */

object Constant {
    object Intent {
        const val KEY = "KEY"
        const val EMAIL = "EMAIL"
        const val PHONE = "PHONE"
    }

    object Serialize {
        const val KEY = "KEY"
    }

    object Parcelize {
        const val KEY = "KEY"
    }

    object Preferences {
        const val PREF_NAME = "MyDoctor"
        object KEY {
            const val TOKEN = "TOKEN"
            const val EMAIL = "EMAIL"
            const val PASSWORD = "PASSWORD"
            const val DARK_MODE = "DARK_MODE"
            const val APP_LANGUAGE = "APP_LANGUAGE"
        }
    }

    object PrefDataStore {
        val COUNTER_KEY = intPreferencesKey("counter_key")
    }
}
