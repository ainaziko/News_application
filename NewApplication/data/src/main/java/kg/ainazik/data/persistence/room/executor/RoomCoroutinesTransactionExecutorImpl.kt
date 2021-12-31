package kg.ainazik.data.persistence.room.executor

import kg.ainazik.data.persistence.room.ApplicationDatabase
import kg.ainazik.domain.interactor.result.CompletableResult
import javax.inject.Inject

class RoomCoroutinesTransactionExecutorImpl @Inject constructor(
    private val database: ApplicationDatabase
) : RoomCoroutinesTransactionExecutor {

    override fun executeQuery(performQuery: (ApplicationDatabase) -> Unit): CompletableResult {
        return try {
            performQuery(database)
            CompletableResult.Success()
        } catch (t: Throwable) {
            CompletableResult.Exception(t)
        }
    }
}
