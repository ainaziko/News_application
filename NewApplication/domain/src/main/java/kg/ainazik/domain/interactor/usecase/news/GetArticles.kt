package kg.ainazik.domain.interactor.usecase.news

import kg.ainazik.domain.interactor.result.Result
import kg.ainazik.domain.interactor.usecase.base.UseCase
import kg.ainazik.domain.model.news.ArticleModel
import kg.ainazik.domain.repository.NewsRepository
import javax.inject.Inject

class GetArticles @Inject constructor(
    private val repository: NewsRepository
) : UseCase<Array<ArticleModel>, Void>(){

    override suspend fun doOnBackground(params: Void?): Result<Array<ArticleModel>> {
        return repository.getArticles()
    }
}