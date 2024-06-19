package pena.camila.alkewalletm5.model

data class User (
    val id: Long,
    val firstname: String,
    val lastname: String,
    val email: String,
    val password: String,
    val roleId: Long = 1,
    val points: Long = 0
)