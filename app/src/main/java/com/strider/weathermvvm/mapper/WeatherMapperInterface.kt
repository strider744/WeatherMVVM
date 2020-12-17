package com.strider.weathermvvm.mapper

interface WeatherMapperInterface<Response, Entity, Store> {

    fun mapToEntity(response: Response): Entity

    fun mapListToEntity(list: List<Response>): List<Entity>

    fun mapFromEntity(entity: Entity): Store

    fun mapListFromEntity(list: List<Entity>): List<Store>
}