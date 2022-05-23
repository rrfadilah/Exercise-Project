package net.mzhasanah.fiveinone.exerciseproject

import androidx.datastore.preferences.core.intPreferencesKey

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
        }
    }

    object Register {
        const val PREF_REGISTER_NAME = "Register"
        object KEY {
            const val EMAIL = "email"
            const val PASSWORD = "password"
            const val NAMA = "nama"
            const val PEKERJAAN = "pekerjaan"
        }
    }

    object PrefDataStore {
        val COUNTER_KEY = intPreferencesKey("counter_key")
    }
}
