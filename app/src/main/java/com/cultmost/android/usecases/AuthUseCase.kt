package com.cultmost.android.usecases

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by Dmitry Bochkov on 03.08.2020.
 */

private const val AUTH_STORAGE = "cultmost_auth_storage"
private const val USER_EMAIL = "email"
private const val FIRST_LAUNCH = "first_launch"

object AuthUseCase {

    private lateinit var sharedPreferences: SharedPreferences

    fun isLoggedIn(appContext: Context) =
        appContext.getSharedPreferences(AUTH_STORAGE, Context.MODE_PRIVATE).contains(
            USER_EMAIL
        )

    fun getUserEmail(appContext: Context) =
        appContext.getSharedPreferences(AUTH_STORAGE, Context.MODE_PRIVATE).getString(
            USER_EMAIL,
            ""
        )

    fun saveUserEmail(appContext: Context, email: String) {
        appContext.getSharedPreferences(AUTH_STORAGE, Context.MODE_PRIVATE).edit()
            .putString(USER_EMAIL, email).apply()
//        sendEmailToAnalytics(email)
    }

//    private fun sendEmailToAnalytics(email: String) {
//        Amplitude.getInstance().userId = email
//    }

    fun isItFirstLaunch(appContext: Context): Boolean =
        appContext.getSharedPreferences(AUTH_STORAGE, Context.MODE_PRIVATE)
            .getBoolean(FIRST_LAUNCH, false)

    fun setFirstLaunchDone(appContext: Context) {
        appContext.getSharedPreferences(AUTH_STORAGE, Context.MODE_PRIVATE).edit()
            .putBoolean(FIRST_LAUNCH, true).apply()
    }

//    fun logAmazonOpen(glassesModel: GlassesModel) {
//        Amplitude.getInstance().logEvent("Exit_to_Amazon_MainScreen")
//    }

    fun logout(appContext: Context) {
        appContext.getSharedPreferences(AUTH_STORAGE, Context.MODE_PRIVATE).edit()
            .remove(USER_EMAIL).apply()
    }
}