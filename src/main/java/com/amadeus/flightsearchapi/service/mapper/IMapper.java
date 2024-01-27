package com.amadeus.flightsearchapi.service.mapper;

import java.util.List;

public interface IMapper<Entity,Request,Response>{
    Entity toEntity(Request request);
    Response toResponse(Entity entity);
    List<Response> toResponseList(List<Entity> entities);
}
