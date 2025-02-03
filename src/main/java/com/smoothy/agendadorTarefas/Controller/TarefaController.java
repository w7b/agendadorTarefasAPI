package com.smoothy.agendadorTarefas.Controller;

import com.smoothy.agendadorTarefas.Business.DTO.TarefasDTO;
import com.smoothy.agendadorTarefas.Business.TarefaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tarefas")
public class TarefaController {
    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefasDTO> salvarTarefas(@RequestBody TarefasDTO dto,
                                    @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(tarefaService.salvarTarefas(token , dto));
    }
}
