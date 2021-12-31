package kg.ainazik.domain.interactor.usecase.news

import kg.ainazik.domain.interactor.result.Result
import kg.ainazik.domain.interactor.usecase.base.UseCase
import kg.ainazik.domain.model.news.NewsModel
import kg.ainazik.domain.repository.NewsRepository
import javax.inject.Inject

class SearchNews @Inject constructor(
    private val repository: NewsRepository
) : UseCase<NewsModel, String>(){
    override suspend fun doOnBackground(params: String?): Result<NewsModel> {
        return repository.searchNews(params)
    }
}