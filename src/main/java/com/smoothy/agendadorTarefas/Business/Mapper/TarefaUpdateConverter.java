package com.smoothy.agendadorTarefas.Business.Mapper;

import com.smoothy.agendadorTarefas.Business.DTO.TarefasDTO;
import com.smoothy.agendadorTarefas.Infrastructure.Entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateConverter {

    void updateTarefas(TarefasDTO dto, @MappingTarget TarefasEntity entity);

}
