package kg.ainazik.data.rest.client

import kg.ainazik.data.rest.api.NewsApiService

interface RestClient {
    fun newsApiService(): NewsApiService
}