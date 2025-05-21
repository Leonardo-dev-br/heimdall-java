package montclio.heimdall.controller;

import jakarta.transaction.Transactional;
import montclio.heimdall.dto.GetMotorcycleDTO;
import montclio.heimdall.dto.PostMotorcycleDTO;
import montclio.heimdall.dto.PutMotorcycleDTO;
import montclio.heimdall.model.Motorcycle;
import montclio.heimdall.model.MotorcycleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import montclio.heimdall.service.MotorcycleService;

import java.net.URI;

@RestController
@RequestMapping("/motorcycle")
public class MotorcycleController {


    @Autowired
    private MotorcycleService motorcycleService;


    // Pegar as Motos cadastradas
    @GetMapping
    public ResponseEntity<Page<GetMotorcycleDTO>> getAllMotocycle(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(motorcycleService.getAllMotocycles(pageable));
    }

    // Pegar uma Moto cadastrada pelo id
    @GetMapping("/{id}")
    public ResponseEntity<GetMotorcycleDTO> getMotorcycleById(@PathVariable Long id){
        return ResponseEntity.ok(motorcycleService.getMotorcycleById(id));
    }

    //Cadastrar uma nova moto
    @PostMapping
    @Transactional
    public ResponseEntity<Void> postMotorcycle(@RequestBody PostMotorcycleDTO motorcycleDTO){
        Motorcycle moto = new Motorcycle(motorcycleDTO);
        motorcycleService.postMotorcycle(motorcycleDTO);
        return ResponseEntity.created(URI.create("/motorcycle/" + moto.getId())).build();
    }

    //Atualizar uma moto
    @PutMapping
    @Transactional
    public ResponseEntity<Void> putMotorcycle(@RequestBody PutMotorcycleDTO motorcycleDTO){
        motorcycleService.putMotorcycle(motorcycleDTO);
        return ResponseEntity.ok().build();
    }
    //Deletar uma moto
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteMotorcycle (@PathVariable Long id){
     motorcycleService.deletMotorcycle(id);
     return  ResponseEntity.noContent().build();
    }


}
