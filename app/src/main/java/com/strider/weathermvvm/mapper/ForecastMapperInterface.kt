package com.strider.weathermvvm.mapper

interface ForecastMapperInterface<Response, ResponseData, Entity, Store, SubData> {

    fun mapToEntity(data:ResponseData, subData: SubData): Entity

    fun mapResponseToEntity(response: Response): List<Entity>

    fun mapFromEntity(entity: Entity): Store

    fun mapListFromEntity(list: List<Entity>): List<Store>
}