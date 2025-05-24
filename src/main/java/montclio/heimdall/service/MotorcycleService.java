package montclio.heimdall.service;

import jakarta.transaction.Transactional;
import montclio.heimdall.dto.GetMotorcycleDTO;
import montclio.heimdall.dto.MotorcycleFilter;
import montclio.heimdall.dto.PostMotorcycleDTO;
import montclio.heimdall.dto.PutMotorcycleDTO;
import montclio.heimdall.exception.ResourceNotFoundException;
import montclio.heimdall.model.Motorcycle;
import montclio.heimdall.model.TagRfId;
import montclio.heimdall.specification.MotorcycleSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import montclio.heimdall.repository.MotorcycleRepository;

@Service
public class MotorcycleService {
    @Autowired
    private MotorcycleRepository motorcycleRepository;

    //Retorna todas as motos cadastradas
    public Page<GetMotorcycleDTO> getAllMotorcycles(MotorcycleFilter filter, Pageable page) {
        Specification<Motorcycle> spec = MotorcycleSpecification.withFilter(filter);
        return motorcycleRepository.findAll(spec, page).map(GetMotorcycleDTO::new);
    }

    //Retorna moto por id
    @Cacheable (value = "motorcycleById", key = "#id")
    public GetMotorcycleDTO getMotorcycleById(Long id){
        return motorcycleRepository.findById(id).map(GetMotorcycleDTO::new).
                orElseThrow(()-> new ResourceNotFoundException("Moto com ID " + id + " não encontrada"));
    }

    //Cadastra uma nova moto
    @Transactional
    @CacheEvict(value = "motorcycles", allEntries = true)
    public Motorcycle postMotorcycle(PostMotorcycleDTO motorcycleDTO){
        Motorcycle motorcycle = new Motorcycle(motorcycleDTO);
        return motorcycleRepository.save(motorcycle);
    }

    //Atualiza dados Moto
    @Transactional
    @CacheEvict(value = {"motorcycles", "motorcycleById"}, allEntries = true)
    public void putMotorcycle ( Long id, PutMotorcycleDTO motorcycleDTO){
        var moto = motorcycleRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Moto com ID " + id + " não encontrada"));
        moto.updateData(motorcycleDTO);
    }

    //deleta os dados da moto
    @Transactional
    @CacheEvict(value = {"motorcycles", "motorcycleById"}, allEntries = true)
    public void deleteMotorcycle(Long id) {
        Motorcycle moto = motorcycleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Moto com id: " + id + " não encontrada"));

        TagRfId tag = moto.getTag();
        if (tag != null) {
            if (tag.getMotorcycle() != null) {
                tag.setMotorcycle(null);
            }
        }

        moto.setTag(null);
        motorcycleRepository.deleteById(id);
    }

}
