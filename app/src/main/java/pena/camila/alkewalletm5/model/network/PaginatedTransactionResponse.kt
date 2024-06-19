package pena.camila.alkewalletm5.model.network

data class PaginatedTransactionResponse(val previousPage: String?,
                                        val nextPage: String?,
                                        val data: List<TransactionResponse>)
