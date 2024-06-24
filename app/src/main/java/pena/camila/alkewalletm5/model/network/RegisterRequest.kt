package pena.camila.alkewalletm5.model.network

class RegisterRequest (

    val first_name: String,
    val last_name: String,
    val email: String,
    val password: String,
    val roleId: Long,
    val points: Long
)