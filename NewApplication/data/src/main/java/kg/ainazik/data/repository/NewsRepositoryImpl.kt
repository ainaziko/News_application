package kg.ainazik.data.repository

import kg.ainazik.data.entity.mapper.news.NewsEntityMapper
import kg.ainazik.data.source.news.NewsDataStoreFactory
import kg.ainazik.domain.interactor.result.CompletableResult
import kg.ainazik.domain.model.news.NewsModel
import kg.ainazik.domain.repository.NewsRepository
import kg.ainazik.domain.interactor.result.Result
import kg.ainazik.domain.model.news.ArticleModel
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val dataStoreFactory: NewsDataStoreFactory,
    private val newsEntityMapper: NewsEntityMapper
) : NewsRepository {

    override fun getBreakingNews(): Result<NewsModel> {
        return dataStoreFactory.retrieveRemoteDataStore().getBreakingNews().map {
            newsEntityMapper.transform(it)
        }
    }

    override fun searchNews(searchQuery: String?): Result<NewsModel> {
        return dataStoreFactory.retrieveRemoteDataStore().searchNews(searchQuery).map {
            newsEntityMapper.transform(it)
        }
    }

    override fun saveArticle(articleModel: ArticleModel?): CompletableResult {
        return dataStoreFactory.retrieveLocalDataStore().saveArticle(articleModel)
    }

    override fun delete(articleModel: ArticleModel?): CompletableResult {
        return dataStoreFactory.retrieveLocalDataStore().delete(articleModel)
    }

    override fun getArticles(): Result<Array<ArticleModel>> {
        return dataStoreFactory.retrieveLocalDataStore().getArticles()
    }
}