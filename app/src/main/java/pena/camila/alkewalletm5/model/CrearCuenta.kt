package pena.camila.alkewalletm5.model


data class CrearCuenta(
    val creationDate: String,
    val money: Long,
    val isBlocked: Boolean,
    val userID: Long
)