package com.smoothy.agendadorTarefas.Controller;

import com.smoothy.agendadorTarefas.Business.DTO.TarefasDTO;
import com.smoothy.agendadorTarefas.Business.TarefaService;
import com.smoothy.agendadorTarefas.Infrastructure.Enums.StatusNotificacao;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefasDTO>> buscaTarefaAgendadaPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal){

        return ResponseEntity.ok(tarefaService.buscaTarefaAgendadaPeriodo(dataInicial, dataFinal));
    }

    @GetMapping
    public ResponseEntity<List<TarefasDTO>> buscaTarefasPorEmail(@RequestHeader("Authorization") String token){
        List<TarefasDTO> tarefas = tarefaService.buscaTarefasPorEmail(token);

        return ResponseEntity.ok(tarefas);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletaPorId(@RequestParam("Id") String Id){

        tarefaService.deletaPorId(Id);

        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<TarefasDTO> alteraStatusNotificacao(@RequestParam("status") StatusNotificacao status,
                                                              @RequestParam("id") String Id){

        return ResponseEntity.ok(tarefaService.alteraStatus(status, Id));
    }

    @PutMapping
    public ResponseEntity<TarefasDTO> updateTarefas(@RequestBody TarefasDTO dto, @RequestParam("id") String Id){
        return ResponseEntity.ok(tarefaService.updateTarefas(dto, Id));
    }

}
