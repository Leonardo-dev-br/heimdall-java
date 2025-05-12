package montclio.heimdall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import montclio.heimdall.model.dto.MotocycleDTO;
import montclio.heimdall.service.MotocycleService;

@RestController
@RequestMapping("/motocycle")
public class MotocycleController {


    @Autowired
    private MotocycleService motocycleService;
    // Pegar as Motos cadastradas
    @GetMapping
    public ResponseEntity<List<MotocycleDTO>> getAllMotocycle(){
        return ResponseEntity.ok(motocycleService.getAllMotocycles());
    }

    // Pegar uma Moto cadastrada pelo id
    @GetMapping("{id}")
    public ResponseEntity<MotocycleDTO> getMotocycleById(@PathVariable Long id){
        return ResponseEntity.ok(motocycleService.getMotocycleById(id));
    }

    //Cadastrar uma nova moto

    //Atualizar uma moto

    //Deletar uma moto
}
