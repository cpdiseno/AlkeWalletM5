package pena.camila.alkewalletm5.model.network


data class CrearCuenta(
    val creationDate: String,
    val money: Long,
    val isBlocked: Boolean,
    val userID: Long
)