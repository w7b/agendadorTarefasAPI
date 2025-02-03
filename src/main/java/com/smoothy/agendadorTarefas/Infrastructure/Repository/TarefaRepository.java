package com.smoothy.agendadorTarefas.Infrastructure.Repository;

import com.smoothy.agendadorTarefas.Infrastructure.Entity.TarefasEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepository extends MongoRepository<TarefasEntity, String> {
}
