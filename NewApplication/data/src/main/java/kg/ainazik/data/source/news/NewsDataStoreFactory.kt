package kg.ainazik.data.source.news

import javax.inject.Inject

class NewsDataStoreFactory @Inject constructor(
    private val remoteDataStore: NewsRemoteDataStore,
    private val localDataStore: NewsLocalDataStore
) {
    fun retrieveRemoteDataStore(): NewsRemoteDataStore {
        return remoteDataStore
    }

    fun retrieveLocalDataStore(): NewsLocalDataStore {
        return localDataStore
    }
}