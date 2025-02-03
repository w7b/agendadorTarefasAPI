package com.smoothy.agendadorTarefas.Infrastructure.Repository;

import com.smoothy.agendadorTarefas.Infrastructure.Entity.TarefasEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TarefaRepository extends MongoRepository<TarefasEntity, String> {
    List<TarefasEntity> findByDataEventoBetween(LocalDateTime dataInicial,  LocalDateTime dataFinal);

    List<TarefasEntity> findByEmailUsuario(String email);
}
