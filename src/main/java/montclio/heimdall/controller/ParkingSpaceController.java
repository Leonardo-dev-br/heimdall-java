package montclio.heimdall.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import montclio.heimdall.dto.ParkingSpaceDTO.GetParkingSpaceDTO;
import montclio.heimdall.dto.ParkingSpaceDTO.PostParkingSpaceDTO;
import montclio.heimdall.dto.ParkingSpaceDTO.PutParkingSpaceDTO;
import montclio.heimdall.dto.ParkingSpaceDTO.ParkingSpaceFilter;
import montclio.heimdall.model.ParkingSpace;
import montclio.heimdall.service.ParkingSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/parkingspaces")
@Tag(name = "ParkingSpaces", description = "Gerencia vagas do pátio (Parking Space)")
public class ParkingSpaceController {

    @Autowired
    private ParkingSpaceService parkingSpaceService;

    @Operation(summary = "Lista parking spaces", description = "Retorna lista paginada de parking spaces")
    @GetMapping
    public ResponseEntity<Page<GetParkingSpaceDTO>> getAllParkingSpaces(
            ParkingSpaceFilter filter,
            @PageableDefault(size = 5, sort = "idVaga", direction = Sort.Direction.ASC) Pageable page
    ) {
        return ResponseEntity.ok(parkingSpaceService.getAllParkingSpaces(filter, page));
    }

    @Operation(summary = "Busca parking space por ID", description = "Retorna parking space por ID")
    @GetMapping("/{id}")
    public ResponseEntity<GetParkingSpaceDTO> getParkingSpaceById(@PathVariable Long id) {
        return ResponseEntity.ok(parkingSpaceService.getParkingSpaceById(id));
    }

    @Operation(summary = "Cria parking space", description = "Cadastra nova vaga (parking space)")
    @PostMapping
    public ResponseEntity<Void> createParkingSpace(@Valid @RequestBody PostParkingSpaceDTO dto) {
        ParkingSpace saved = parkingSpaceService.postParkingSpace(dto);
        return ResponseEntity.created(URI.create("/parkingspaces/" + saved.getIdVaga())).build();
    }

    @Operation(summary = "Atualiza parking space", description = "Atualiza dados da vaga")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateParkingSpace(@PathVariable Long id, @Valid @RequestBody PutParkingSpaceDTO dto) {
        parkingSpaceService.putParkingSpace(id, dto);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Remove parking space", description = "Deleta parking space por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParkingSpace(@PathVariable Long id) {
        parkingSpaceService.deleteParkingSpace(id);
        return ResponseEntity.noContent().build();
    }
}
