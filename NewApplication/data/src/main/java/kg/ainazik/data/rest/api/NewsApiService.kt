package kg.ainazik.data.rest.api

import kg.ainazik.domain.interactor.result.Result
import kg.ainazik.data.BuildConfig
import kg.ainazik.data.entity.news.NewsEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("/v2/top-headlines")
    fun getBreakingNews(
        @Query("country")
        countryCode: String? = "US",
        @Query("page")
        page: Int = 1,
        @Query("apiKey")
        apiKey: String = BuildConfig.API_KEY
    ): Result<NewsEntity>

    @GET("/v2/everything")
    fun searchNews(
        @Query("q")
        searchQuery: String?,
        @Query("page")
        page: Int = 1,
        @Query("apiKey")
        apiKey: String = BuildConfig.API_KEY
    ): Result<NewsEntity>

}