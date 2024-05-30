package pena.camila.alkewalletm5.model

//data class Transaction()
data class Transaccion(
    val amount: Long,
    val concept: String,
    val date: String,
    val type: String,
    val accountID: Long,
    val userID: Long,
    val toAccountID: Long
)