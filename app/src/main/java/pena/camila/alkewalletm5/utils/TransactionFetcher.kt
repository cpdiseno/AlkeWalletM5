package pena.camila.alkewalletm5.utils

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pena.camila.alkewalletm5.data.repository.TransactionRepository
import pena.camila.alkewalletm5.model.database.TransactionEntity

class TransactionFetcher(
    private val transactionRepository: TransactionRepository
) {

    fun fetchTransactions(token: String, onComplete: (Boolean, List<TransactionEntity>?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val transactionsResponse = transactionRepository.fetchTransactionsFromApi("Bearer $token")
                val transactionsList = transactionsResponse.data.map { response ->
                    TransactionEntity(
                        amount = response.amount,
                        concept = response.concept,
                        date = response.date,
                        type = response.type,
                        accountID = response.accountId,
                        userID = response.userId,
                        toAccountID = response.toAccountId
                    )
                }
                transactionRepository.deleteAllTransactions()
                transactionRepository.saveTransactions(transactionsList)
                onComplete(true, transactionsList)
            } catch (e: Exception) {
                Log.e("TransactionFetcher", "Error fetching transactions", e)
                onComplete(false, null)
            }
        }
    }
}