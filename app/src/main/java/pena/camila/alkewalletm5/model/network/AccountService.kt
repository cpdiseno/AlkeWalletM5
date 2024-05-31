package pena.camila.alkewalletm5.model.network

import pena.camila.alkewalletm5.model.RegisterRequest
import pena.camila.alkewalletm5.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface AccountService {

    @POST("users")
    fun crearUsuario(@Body usuarioCreado: RegisterRequest): Call<RegisterResponse>
}

