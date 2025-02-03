package com.smoothy.agendadorTarefas.Infrastructure.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.smoothy.agendadorTarefas.Infrastructure.Enums.StatusNotificacao;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("tarefa")
public class TarefasEntity {

    @Id
    private String id;
    private String emailUsuario;
    private String nomeTarefa;
    private String descricao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataEvento;
    private LocalDateTime dataAlteracao;
    private StatusNotificacao statusNotificacao;
}
