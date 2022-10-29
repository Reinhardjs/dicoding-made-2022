package com.example.core.utils

import com.example.core.data.source.local.entity.MarvelEventEntity
import com.example.core.data.source.remote.response.marvelevent.MarvelEventResponse
import com.example.core.domain.model.MarvelEvent

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

    fun mapEntitiesToDomain(input: List<MarvelEventEntity>): List<MarvelEvent> =
        input.map {
            MarvelEvent(
                id = it.marvelEventId,
                title = it.title,
                description =  it.description
            )
        }

    fun mapDomainToEntity(input: MarvelEvent) =
        MarvelEventEntity(
            marvelEventId = input.id,
            title = input.title,
            description = input.description,
            thumbnailUrl = "${input.thumbnail?.path}.${input.thumbnail?.extension}"
        )
}