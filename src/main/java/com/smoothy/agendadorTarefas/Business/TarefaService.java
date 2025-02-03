package com.smoothy.agendadorTarefas.Business;

import com.smoothy.agendadorTarefas.Business.DTO.TarefasDTO;
import com.smoothy.agendadorTarefas.Business.Mapper.TarefaConverter;
import com.smoothy.agendadorTarefas.Infrastructure.Entity.TarefasEntity;
import com.smoothy.agendadorTarefas.Infrastructure.Enums.StatusNotificacao;
import com.smoothy.agendadorTarefas.Infrastructure.Repository.TarefaRepository;
import com.smoothy.agendadorTarefas.Infrastructure.Security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor

public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefaConverter tarefaConverter;
    private final JwtUtil jwtUtil;


    public TarefasDTO salvarTarefas(String token, TarefasDTO dto){

        String email = jwtUtil.extrairEmailToken(token.substring(7));

        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacao(StatusNotificacao.PENDENTE);
        dto.setEmailUsuario(email);

        TarefasEntity entity = tarefaConverter.paraTarefaEntity(dto);

        return tarefaConverter.paraTarefasDTO(
                tarefaRepository.save(entity));
    }
}
