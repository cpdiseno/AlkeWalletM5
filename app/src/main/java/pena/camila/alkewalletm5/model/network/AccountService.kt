package pena.camila.alkewalletm5.model.network

import pena.camila.alkewalletm5.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface AccountService {


    @GET("auth/me")
    fun obtenerInfoLogin(
        @Header("Authorization") token: String
    ): Call<User>

}