package pena.camila.alkewalletm5.model.network

data class AccountResponse(
    val id: Long,
    val creationDate: Any? = null,
    val money: Double?,
    val isBlocked: Boolean,
    val userId: Long,
    val createdAt: String,
    val updatedAt: String
)
