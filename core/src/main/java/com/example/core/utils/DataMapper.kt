package com.example.core.utils

import com.example.core.data.source.local.entity.MarvelEventEntity
import com.example.core.data.source.remote.response.marvelevent.MarvelEventResponse
import com.example.core.domain.model.MarvelEvent
import com.example.core.domain.model.Thumbnail

object DataMapper {
    fun mapResponsesToEntities(input: List<MarvelEventResponse>): List<MarvelEventEntity> {
        val movieList = ArrayList<MarvelEventEntity>()
        input.map { movieResponse ->

            val movie = MarvelEventEntity(
                marvelEventId = movieResponse.id,
                title = movieResponse.title,
                description = movieResponse.description,
                thumbnailUrl = "${movieResponse.thumbnail?.path}.${movieResponse.thumbnail?.extension}"
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MarvelEventEntity>): List<MarvelEvent> {
        return input.map {
            MarvelEvent(
                id = it.marvelEventId,
                title = it.title,
                description = it.description,
                thumbnail = Thumbnail(
                    path = it.thumbnailUrl?.split(".")?.get(0),
                    extension = it.thumbnailUrl?.split(".")?.get(1)
                )
            )
        }
    }

    fun mapDomainToEntity(input: MarvelEvent) =
        MarvelEventEntity(
            marvelEventId = input.id,
            title = input.title,
            description = input.description,
            thumbnailUrl = "${input.thumbnail?.path}.${input.thumbnail?.extension}"
        )
}