package kg.ainazik.data.persistence.room.entity.mapper

import kg.ainazik.data.entity.mapper.base.BaseEntityMapper
import kg.ainazik.data.persistence.room.entity.ArticleRoomEntity
import kg.ainazik.domain.model.news.ArticleModel
import kg.ainazik.domain.model.news.SourceModel
import javax.inject.Inject

class ArticleRoomEntityMapper @Inject constructor(
) : BaseEntityMapper<ArticleModel, ArticleRoomEntity>(){

    override fun transformToEntity(entity: ArticleRoomEntity?): ArticleModel {
        val model = SourceModel(entity?.source, entity?.source)
        return ArticleModel(
            author = entity?.author,
            content = entity?.content,
            description = entity?.description,
            publishedAt = entity?.publishedAt,
            sourceModel = model,
            title = entity?.title,
            url = entity?.url,
            urlToImage = entity?.urlToImage,
            isSaved = entity?.isSaved ?: false
        )
    }

    override fun transformToModel(entity: ArticleModel?): ArticleRoomEntity {
        return ArticleRoomEntity(
            author = entity?.author,
            content = entity?.content,
            description = entity?.description,
            publishedAt = entity?.publishedAt,
            source = entity?.sourceModel?.name,
            title = entity?.title,
            url = entity?.url ?: "",
            urlToImage = entity?.urlToImage,
            isSaved = entity?.isSaved ?: false
        )
    }
}