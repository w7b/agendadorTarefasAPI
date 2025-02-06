package com.smoothy.agendadorTarefas.Business;

import com.smoothy.agendadorTarefas.Business.DTO.TarefasDTO;
import com.smoothy.agendadorTarefas.Business.Mapper.TarefaConverter;
import com.smoothy.agendadorTarefas.Business.Mapper.TarefaUpdateConverter;
import com.smoothy.agendadorTarefas.Infrastructure.Entity.TarefasEntity;
import com.smoothy.agendadorTarefas.Infrastructure.Enums.StatusNotificacao;
import com.smoothy.agendadorTarefas.Infrastructure.Exceptions.ResourceNotFoundException;
import com.smoothy.agendadorTarefas.Infrastructure.Repository.TarefaRepository;
import com.smoothy.agendadorTarefas.Infrastructure.Security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefaConverter tarefaConverter;
    private final TarefaUpdateConverter tarefaUpdateConverter;
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

    public List<TarefasDTO> buscaTarefaAgendadaPeriodo(LocalDateTime dataInicial,
                                                       LocalDateTime dataFinal ) {

        return tarefaConverter.paraListaTarefasDTO(
                tarefaRepository.findByDataEventoBetweenAndStatusNotificacao(
                        dataInicial ,
                        dataFinal ,
                        StatusNotificacao.PENDENTE));
    }

    public List<TarefasDTO> buscaTarefasPorEmail(String token){
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        List<TarefasEntity> listaTarefas = tarefaRepository.findByEmailUsuario(email);

        return tarefaConverter.paraListaTarefasDTO(listaTarefas);
    }

    public void deletaPorId(String Id){
        try {
            tarefaRepository.deleteById(Id);

        } catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("ID Nao identificado" + e.getCause());
        }
    }

    public TarefasDTO alteraStatus(StatusNotificacao status, String Id){
        try{
            TarefasEntity entity = tarefaRepository.findById(Id).
                orElseThrow(() -> new ResourceNotFoundException("Tarefa nao encontrada"));
            entity.setStatusNotificacao(status);
            return tarefaConverter.paraTarefasDTO(tarefaRepository.save(entity));

        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa " +e.getCause());
        }
    }

    public TarefasDTO updateTarefas(TarefasDTO dto, String Id ){
        try {
            TarefasEntity entity = tarefaRepository.findById(Id).
                    orElseThrow(() -> new ResourceNotFoundException("Tarefa nao encontrada"));
            tarefaUpdateConverter.updateTarefas(dto, entity);
            return tarefaConverter.paraTarefasDTO(tarefaRepository.save(entity));

        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa " +e.getCause());
        }
    }

}
