package com.smoothy.agendadorTarefas.Business.Mapper;
import com.smoothy.agendadorTarefas.Business.DTO.TarefasDTO;
import com.smoothy.agendadorTarefas.Infrastructure.Entity.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefaConverter {

    TarefasEntity paraTarefaEntity(TarefasDTO dto);

    TarefasDTO paraTarefasDTO(TarefasEntity entity);

}
