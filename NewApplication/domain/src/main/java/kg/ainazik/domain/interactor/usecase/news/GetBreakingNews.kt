package kg.ainazik.domain.interactor.usecase.news

import kg.ainazik.domain.interactor.result.Result
import kg.ainazik.domain.interactor.usecase.base.UseCase
import kg.ainazik.domain.model.news.NewsModel
import kg.ainazik.domain.repository.NewsRepository
import javax.inject.Inject

class GetBreakingNews @Inject constructor(
    private val repository: NewsRepository
) : UseCase<NewsModel, Void>() {
    override suspend fun doOnBackground(params: Void?): Result<NewsModel> {
        return repository.getBreakingNews()
    }
}