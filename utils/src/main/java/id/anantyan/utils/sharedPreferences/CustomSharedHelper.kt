package id.anantyan.utils.sharedPreferences

interface PreferenceHelper {
    fun setFullname(value: String)
    fun setProfession(value: String)
    fun setEmail(value: String)
    fun setPassword(value: String)
    fun getFullname(): String?
    fun getProfession(): String?
    fun getEmail(): String?
    fun getPassword(): String?
}