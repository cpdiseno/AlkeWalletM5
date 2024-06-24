package pena.camila.alkewalletm5.model.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Header

/**
 * Api service que contiene los metodos para logear al usuario
 */
interface LoginService {
    /**
     * Servicio que hace login, recibe como parametros el email y la contraseña encapsulados un objeto
     */
    @POST("auth/login")
    fun hacerLogin(@Body request: LoginRequest): Call<LoginResponse>


    @GET("auth/me")
    fun obtenerInfoLogin(
        @Header("Authorization") token: String
    ): Call<User>

}