package  pena.camila.alkewalletm5.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.OptIn
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import pena.camila.alkewalletm5.model.network.User


class SharedPreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)

    fun saveAuthToken(token: String) {
        val editor = sharedPreferences.edit()
        editor.putString("token", token)
        editor.apply()
    }

    @SuppressLint("Range")
    @OptIn(UnstableApi::class)
    fun getAuthToken(): String? {
        val token = sharedPreferences.getString("token", null)
        Log.d("SharedPreferencesManager", "Retrieved token: $token")
        return token
    }

    fun saveUser(user: User) {
        sharedPreferences.edit().apply {
            putString("fn", user.firstName)
            putString("ln", user.lastName)
            putString("em", user.email)
            putString("pwd", user.password)
            putInt("rid", user.roleId)
            putInt("pts", user.points)
            apply()
        }
    }

    fun getUser(): User? {
        val firstName = sharedPreferences.getString("fn", null)
        val lastName = sharedPreferences.getString("ln", null)
        val email = sharedPreferences.getString("em", null)
        val password = sharedPreferences.getString("pwd", null)
        val roleId = sharedPreferences.getInt("rid", -1)
        val points = sharedPreferences.getInt("pts", -1)

        return if (firstName != null && lastName != null && email != null && password != null && roleId != -1 && points != -1) {
            User(firstName, lastName, email, password, roleId, points)
        } else {
            null
        }
    }

    @SuppressLint("Range")
    @OptIn(UnstableApi::class)
    fun saveAccountData(money: String, id: Long) {
        val editor = sharedPreferences.edit()
        editor.putLong("aid", id)
        editor.putString("mny", money)
        editor.apply()
        Log.d("SharedPreferencesManager", "Account ID saved: $id")
    }

    @SuppressLint("Range")
    @OptIn(UnstableApi::class)
    fun getAccountId(): Long? {
        val accountId = sharedPreferences.getLong("aid", -1)
        Log.d("SharedPreferencesManager", "Retrieved account ID: $accountId")
        return if (accountId != -1L) accountId else null
    }

    fun getSaldo(): String? {
        return sharedPreferences.getString("mny", "0")
    }

    @OptIn(UnstableApi::class)
    @SuppressLint("Range")
    fun clearSession() {
        sharedPreferences.edit().clear().apply()
        Log.d("SharedPreferencesManager", "Session cleared")
    }
}