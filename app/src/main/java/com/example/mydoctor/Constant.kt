package com.example.mydoctor

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
            const val EMAIL = "EMAIL"
            const val PASSWORD = "PASSWORD"
            const val DARK_MODE = "DARK_MODE"
            const val APP_LANGUAGE = "APP_LANGUAGE"
        }
    }

    object Register {
        const val PREF_REGISTER = "Register"
        object KEY {
            const val FULLNAME = "FULLNAME"
            const val EMAIL = "EMAIL"
            const val PASSWORD = "PASSWORD"
            const val PEKERJAAN = "PEKERJAAN"
        }
    }
}