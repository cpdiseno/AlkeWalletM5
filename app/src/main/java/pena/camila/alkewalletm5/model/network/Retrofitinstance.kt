package pena.camila.alkewalletm5.model.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Se configura retrofit para que se pueda consumir la api
class Retrofitinstance {
    companion object {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://wallet-main.eba-ccwdurgr.us-east-1.elasticbeanstalk.com/")
            .addConverterFactory(GsonConverterFactory.create())    //obtiene Json y lo convierte a objeto
            .build()
    }
}