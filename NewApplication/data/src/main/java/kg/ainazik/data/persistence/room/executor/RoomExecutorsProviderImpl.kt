package kg.ainazik.data.persistence.room.executor

import kg.ainazik.data.persistence.room.provider.RoomProvider
import javax.inject.Inject

class RoomExecutorsProviderImpl @Inject constructor(
    private val roomProvider: RoomProvider
) : RoomExecutorsProvider {

    override fun provideQueryExecutor(): RoomCoroutinesQueryExecutor {
        return RoomCoroutinesQueryExecutorImpl(roomProvider.provide())
    }

    override fun provideTransactionExecutor(): RoomCoroutinesTransactionExecutor {
        return RoomCoroutinesTransactionExecutorImpl(roomProvider.provide())
    }
}
