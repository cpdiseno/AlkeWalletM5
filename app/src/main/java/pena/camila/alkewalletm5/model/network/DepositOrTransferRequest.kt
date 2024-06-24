package pena.camila.alkewalletm5.model.network

data class DepositOrTransferRequest(
    val type: String,
    val concept: String,
    val amount: Long
)
