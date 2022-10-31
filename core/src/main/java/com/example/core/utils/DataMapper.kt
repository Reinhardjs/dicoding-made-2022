package com.example.core.utils

import com.example.core.data.source.local.entity.MarvelEventEntity
import com.example.core.data.source.remote.response.marvelevent.MarvelEventResponse
import com.example.core.domain.model.MarvelEvent
import com.example.core.domain.model.Thumbnail
import com.example.core.domain.model.URLs

object DataMapper {
    fun mapResponsesToEntities(input: List<MarvelEventResponse>): List<MarvelEventEntity> {
        val marvelList = ArrayList<MarvelEventEntity>()
        input.map { marvelResponse ->
            val marvel = MarvelEventEntity(
                marvelEventId = marvelResponse.id,
                title = marvelResponse.title,
                description = marvelResponse.description,
                thumbnailUrl = "${marvelResponse.thumbnail?.path}.${marvelResponse.thumbnail?.extension}",
                detailUrl = marvelResponse.urls?.get(0)?.url
            )
            marvelList.add(marvel)
        }
        return marvelList
    }

    fun mapEntitiesToDomain(input: List<MarvelEventEntity>): List<MarvelEvent> {
        return input.map {
            MarvelEvent(
                id = it.marvelEventId,
                title = it.title,
                description = it.description,
                thumbnail = Thumbnail(
                    path = it.thumbnailUrl?.substringBeforeLast("."),
                    extension = it.thumbnailUrl?.substringAfterLast(".")
                ),
                isFavorite = it.isFavorite,
                urls = listOf(URLs(url = it.detailUrl))
            )
        }
    }

    fun mapDomainToEntity(input: MarvelEvent) =
        MarvelEventEntity(
            marvelEventId = input.id,
            title = input.title,
            description = input.description,
            thumbnailUrl = "${input.thumbnail?.path}.${input.thumbnail?.extension}",
            isFavorite = input.isFavorite,
            detailUrl = input.urls?.get(0)?.url
        )
}