package kg.ainazik.domain.repository

import kg.ainazik.domain.interactor.result.CompletableResult
import kg.ainazik.domain.model.news.NewsModel
import kg.ainazik.domain.interactor.result.Result
import kg.ainazik.domain.model.news.ArticleModel

interface NewsRepository {

    fun getBreakingNews() : Result<NewsModel>
    fun searchNews(searchQuery: String?) : Result<NewsModel>

    fun saveArticle(articleModel: ArticleModel?) : CompletableResult
    fun delete(articleModel: ArticleModel?) : CompletableResult
    fun getArticles() : Result<Array<ArticleModel>>
}