package kg.ainazik.newapplication.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kg.ainazik.data.entity.mapper.news.NewsEntityMapper
import kg.ainazik.data.persistence.room.executor.RoomExecutorsProvider
import kg.ainazik.data.persistence.room.executor.RoomExecutorsProviderImpl
import kg.ainazik.data.persistence.room.provider.RoomProvider
import kg.ainazik.data.persistence.room.provider.RoomProviderImpl
import kg.ainazik.data.repository.NewsRepositoryImpl
import kg.ainazik.data.rest.client.RestClient
import kg.ainazik.data.rest.client.RestClientImpl
import kg.ainazik.data.source.news.NewsDataStore
import kg.ainazik.data.source.news.NewsDataStoreFactory
import kg.ainazik.data.source.news.NewsLocalDataStore
import kg.ainazik.data.source.news.NewsRemoteDataStore
import kg.ainazik.domain.repository.NewsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Singleton
    @Provides
    fun provideRestClient(): RestClient {
        return RestClientImpl()
    }

    @Singleton
    @Provides
    fun provideNewsRepository(
        dataStoreFactory: NewsDataStoreFactory,
        newsEntityMapper: NewsEntityMapper
    ): NewsRepository {
        return NewsRepositoryImpl(
            dataStoreFactory = dataStoreFactory,
            newsEntityMapper = newsEntityMapper
        )
    }

    @Provides
    @Singleton
    fun provideRoom(
        context: Application
    ): RoomProvider {
        return RoomProviderImpl(context)
    }

    @Provides
    fun provideRoomExecutorsProvider(
        roomProvider: RoomProvider
    ): RoomExecutorsProvider {
        return RoomExecutorsProviderImpl(roomProvider)
    }
}