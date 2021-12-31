package kg.ainazik.data.source.news

import kg.ainazik.data.entity.news.NewsEntity
import kg.ainazik.domain.interactor.result.CompletableResult
import kg.ainazik.domain.interactor.result.Result
import kg.ainazik.domain.model.news.ArticleModel

interface NewsDataStore {
    fun getBreakingNews() : Result<NewsEntity>
    fun searchNews(searchQuery: String?) : Result<NewsEntity>

    fun saveArticle(articleModel: ArticleModel?) : CompletableResult
    fun delete(articleModel: ArticleModel?) : CompletableResult
    fun getArticles() : Result<Array<ArticleModel>>
}