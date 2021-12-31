package kg.ainazik.domain.model.news

import java.io.Serializable

data class NewsModel(
    val articles: List<ArticleModel>?,
    val status: String?,
    val totalResults: Int?
) : Serializable