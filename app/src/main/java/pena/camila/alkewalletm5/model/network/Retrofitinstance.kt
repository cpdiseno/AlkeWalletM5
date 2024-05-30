package pena.camila.alkewalletm5.model.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Se configura retrofit
class Retrofitinstance {
    companion object {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://wallet-main.eba-ccwdurgr.us-east-1.elasticbeanstalk.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}