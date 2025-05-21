package montclio.heimdall.service;

import montclio.heimdall.dto.GetMotorcycleDTO;
import montclio.heimdall.dto.PostMotorcycleDTO;
import montclio.heimdall.dto.PutMotorcycleDTO;
import montclio.heimdall.model.Motorcycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import montclio.heimdall.repository.MotorcycleRepository;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class MotorcycleService {
    @Autowired
    private MotorcycleRepository motocycleRepository;

    //Retorna todas as motos cadastradas
    public Page<GetMotorcycleDTO> getAllMotocycles(Pageable page) {
        return motocycleRepository.findAll(page).map(GetMotorcycleDTO::new);
    }

    //Retorna moto por id
    public GetMotorcycleDTO getMotorcycleById(Long id){
        return motocycleRepository.findById(id).map(GetMotorcycleDTO::new).
                orElseThrow(()-> new RuntimeException("Moto não encontrada"));
    }

    //Cadastra uma nova moto
    public void postMotorcycle(PostMotorcycleDTO motorcycleDTO){
        Motorcycle motorcycle = new Motorcycle(motorcycleDTO);
        System.out.println(motorcycle);
        motocycleRepository.save(motorcycle);
    }

    //Atualiza dados Moto
    public void putMotorcycle (PutMotorcycleDTO motorcycleDTO){
        var moto = motocycleRepository.getReferenceById(motorcycleDTO.id());
        moto.updateData(motorcycleDTO);
    }

    //Deleta uma moto
    public void deletMotorcycle (Long id){
        motocycleRepository.deleteById(id);
    }

}
