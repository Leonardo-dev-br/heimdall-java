package montclio.heimdall.controller;


import jakarta.validation.Valid;
import montclio.heimdall.dto.GetMotorcycleDTO;
import montclio.heimdall.dto.PostMotorcycleDTO;
import montclio.heimdall.dto.PutMotorcycleDTO;
import montclio.heimdall.model.Motorcycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import montclio.heimdall.service.MotorcycleService;

import java.net.URI;

@RestController
@RequestMapping("/motorcycles")
public class MotorcycleController {


    @Autowired
    private MotorcycleService motorcycleService;


    // Pegar as Motos cadastradas
    @GetMapping
    public ResponseEntity<Page<GetMotorcycleDTO>> getAllMotocycles(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable page) {
        return ResponseEntity.ok(motorcycleService.getAllMotocycles(page));
    }

    // Pegar uma Moto cadastrada pelo id
    @GetMapping("/{id}")
    public ResponseEntity<GetMotorcycleDTO> getMotorcycleById(@PathVariable Long id){
        return ResponseEntity.ok(motorcycleService.getMotorcycleById(id));
    }

    //Cadastrar uma nova moto
    @PostMapping
    public ResponseEntity<Void> postMotorcycle(@Valid @RequestBody PostMotorcycleDTO motorcycleDTO){
        Motorcycle savedMotorcycle = motorcycleService.postMotorcycle(motorcycleDTO);
        return ResponseEntity.created(URI.create("/motorcycles/" + savedMotorcycle.getId())).build();
    }

    //Atualizar uma moto
    @PutMapping("/{id}")
    public ResponseEntity<Void> putMotorcycle( @PathVariable Long id, @Valid @RequestBody PutMotorcycleDTO motorcycleDTO){
        motorcycleService.putMotorcycle(id, motorcycleDTO);
        return ResponseEntity.noContent().build();
    }
    //Deletar uma moto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMotorcycle (@PathVariable Long id){
     motorcycleService.deleteMotorcycle(id);
     return  ResponseEntity.noContent().build();
    }


}
