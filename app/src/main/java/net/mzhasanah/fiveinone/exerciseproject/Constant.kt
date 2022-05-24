package net.mzhasanah.fiveinone.exerciseproject

import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

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
        const val PREF_NAME = "MyDoctor"
        val COUNTER_KEY = intPreferencesKey("counter_key")
        val TOKEN_KEY = stringPreferencesKey("TOKEN")
    }

    object Named {
        const val RETROFIT_MYDOCTOR = "RETROFIT_MYDOCTOR"
        const val RETROFIT_IMAGE = "RETROFIT_IMAGE"

        const val BASE_URL_MYDOCTOR = "BASE_URL_MYDOCTOR"
        const val BASE_URL_MOCK = "BASE_URL_MOCK"
        const val BASE_URL_FLAVOR = "BASE_URL_FLAVOR"
        const val BASE_URL_IMAGE = "BASE_URL_IMAGE"

        const val APIKEY_IMAGE = "APIKEY_IMAGE"
    }
}
