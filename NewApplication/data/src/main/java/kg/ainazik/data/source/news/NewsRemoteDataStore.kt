package kg.ainazik.data.source.news

import kg.ainazik.data.entity.news.NewsEntity
import kg.ainazik.data.persistence.room.entity.ArticleRoomEntity
import kg.ainazik.data.rest.client.RestClient
import kg.ainazik.domain.interactor.result.CompletableResult
import kg.ainazik.domain.interactor.result.Result
import kg.ainazik.domain.model.news.ArticleModel
import javax.inject.Inject

class NewsRemoteDataStore @Inject constructor(
    private val restClient: RestClient
) : NewsDataStore {

    override fun getBreakingNews(): Result<NewsEntity> {
        return restClient.newsApiService().getBreakingNews()
    }

    override fun searchNews(searchQuery: String?): Result<NewsEntity> {
        return restClient.newsApiService().searchNews(searchQuery)
    }

    override fun saveArticle(articleModel: ArticleModel?): CompletableResult {
        throw UnsupportedOperationException()
    }

    override fun delete(articleModel: ArticleModel?): CompletableResult {
        throw UnsupportedOperationException()
    }

    override fun getArticles(): Result<Array<ArticleModel>> {
        throw UnsupportedOperationException()
    }
}