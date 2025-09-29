package montclio.heimdall.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import montclio.heimdall.dto.HistoryDTO.GetHistoryDTO;
import montclio.heimdall.dto.HistoryDTO.HistoryFilter;
import montclio.heimdall.dto.HistoryDTO.PostHistoryDTO;
import montclio.heimdall.dto.HistoryDTO.PutHistoryDTO;
import montclio.heimdall.model.HistoryMoto;
import montclio.heimdall.service.HistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/historicos")
@Tag(name = "Historicos", description = "Gerencia histórico de atividades das motos")


public class HistoryController {


    @Autowired
    private HistoryService historicoService;

    @Operation(summary = "Lista históricos", description = "Retorna lista paginada de históricos")
    @GetMapping
    public ResponseEntity<Page<GetHistoryDTO>> getAllHistoricos(
            HistoryFilter filter,
            @PageableDefault(size = 5, sort = "idHistorico", direction = Sort.Direction.ASC) Pageable page
    ) {
        return ResponseEntity.ok(historicoService.getAllHistoricos(filter, page));
    }

    @Operation(summary = "Busca histórico por ID", description = "Retorna histórico por ID")
    @GetMapping("/{id}")
    public ResponseEntity<GetHistoryDTO> getHistoricoById(@PathVariable Long id) {
        return ResponseEntity.ok(historicoService.getHistoricoById(id));
    }

    @Operation(summary = "Cria um histórico", description = "Cadastra um novo histórico")
    @PostMapping
    public ResponseEntity<Void> createHistorico(@Valid @RequestBody PostHistoryDTO dto) {
        HistoryMoto saved = historicoService.postHistorico(dto);
        return ResponseEntity.created(URI.create("/historicos/" + saved.getIdHistorico())).build();
    }

    @Operation(summary = "Atualiza histórico", description = "Atualiza dados do histórico")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateHistorico(@PathVariable Long id, @Valid @RequestBody PutHistoryDTO dto) {
        historicoService.putHistorico(id, dto);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Remove histórico", description = "Deleta histórico por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHistorico(@PathVariable Long id) {
        historicoService.deleteHistorico(id);
        return ResponseEntity.noContent().build();
    }

}
