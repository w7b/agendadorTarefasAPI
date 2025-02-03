package com.smoothy.agendadorTarefas.Business.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.smoothy.agendadorTarefas.Infrastructure.Enums.StatusNotificacao;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TarefasDTO {

    private String id;
    private String emailUsuario;
    private String nomeTarefa;
    private String descricao;
    private LocalDateTime dataCriacao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataEvento;
    private LocalDateTime dataAlteracao;
    private StatusNotificacao statusNotificacao;
}
