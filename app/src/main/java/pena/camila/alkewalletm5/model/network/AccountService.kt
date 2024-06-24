package pena.camila.alkewalletm5.model.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface AccountService {

    @POST("users")
    fun crearUsuario(@Body usuarioCreado: RegisterRequest): Call<RegisterResponse>
}

