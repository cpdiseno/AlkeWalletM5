package pena.camila.alkewalletm5.model.network

data class LoginResponse(
    val accessToken: String?,
    val error: String?,
    val status: Int?
)