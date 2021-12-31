package kg.ainazik.data.source.news

import kg.ainazik.data.entity.news.NewsEntity
import kg.ainazik.data.persistence.room.entity.ArticleRoomEntity
import kg.ainazik.data.persistence.room.entity.mapper.ArticleRoomEntityMapper
import kg.ainazik.data.persistence.room.executor.RoomExecutorsProvider
import kg.ainazik.domain.interactor.result.CompletableResult
import kg.ainazik.domain.interactor.result.Result
import kg.ainazik.domain.model.news.ArticleModel
import javax.inject.Inject

class NewsLocalDataStore @Inject constructor(
    private val roomExecutorsProvider: RoomExecutorsProvider,
    private val articleRoomEntityMapper: ArticleRoomEntityMapper
) : NewsDataStore {

    override fun getBreakingNews(): Result<NewsEntity> {
        throw UnsupportedOperationException()
    }

    override fun searchNews(searchQuery: String?): Result<NewsEntity> {
        throw UnsupportedOperationException()
    }

    override fun saveArticle(articleModel: ArticleModel?): CompletableResult {
        val model = articleRoomEntityMapper.transformToModel(articleModel)
        return roomExecutorsProvider.provideTransactionExecutor().executeQuery { db ->
            db.newsDao().saveArticle(model)
        }
    }

    override fun delete(articleModel: ArticleModel?): CompletableResult {
        val model = articleRoomEntityMapper.transformToModel(articleModel)
        return roomExecutorsProvider.provideTransactionExecutor().executeQuery { db ->
            db.newsDao().deleteArticle(model)
        }
    }

    override fun getArticles(): Result<Array<ArticleModel>> {
        return roomExecutorsProvider.provideQueryExecutor().executeQuery { db ->
            articleRoomEntityMapper.transformToEntityCollection(
                db.newsDao().getArticles().toMutableList()
            ).toTypedArray()
        }
    }
}