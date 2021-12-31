package kg.ainazik.data.persistence.room.executor

import kg.ainazik.data.persistence.room.ApplicationDatabase
import kg.ainazik.domain.interactor.result.CompletableResult

interface RoomCoroutinesTransactionExecutor {
    fun executeQuery(performQuery: (ApplicationDatabase) -> Unit): CompletableResult
}