package kg.ainazik.data.persistence.room.executor

interface RoomExecutorsProvider {
    fun provideQueryExecutor(): RoomCoroutinesQueryExecutor
    fun provideTransactionExecutor(): RoomCoroutinesTransactionExecutor
}
