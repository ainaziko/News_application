package kg.ainazik.domain.interactor.usecase.news

import kg.ainazik.domain.interactor.result.CompletableResult
import kg.ainazik.domain.interactor.usecase.base.CompletableUseCase
import kg.ainazik.domain.model.news.ArticleModel
import kg.ainazik.domain.repository.NewsRepository
import javax.inject.Inject

class DeleteArticle @Inject constructor(
    private val repository: NewsRepository
) : CompletableUseCase<ArticleModel>() {

    override suspend fun doOnBackground(params: ArticleModel?): CompletableResult {
        return repository.delete(params)
    }
}