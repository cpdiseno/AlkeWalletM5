package pena.camila.alkewalletm5.model.network

import com.google.gson.annotations.SerializedName

data class RegisterRequest (

    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    val email: String,
    val password: String,
    val roleId: Int,
    val points: Int
)
