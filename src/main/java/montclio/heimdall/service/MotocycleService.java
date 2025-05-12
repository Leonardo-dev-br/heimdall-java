package montclio.heimdall.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import montclio.heimdall.model.dto.MotocycleDTO;
import montclio.heimdall.model.entity.MotocycleEntity;
import montclio.heimdall.model.repository.MotocycleRepository;

@Service
public class MotocycleService {
    @Autowired
    private MotocycleRepository motocycleRepository;

    public MotocycleDTO convertToDTO(MotocycleEntity entity) {
        return new MotocycleDTO(
            entity.getIdMotocycle(),
            entity.getPlate(),
            entity.getChassiNumber()
        );
    }

    public List<MotocycleDTO> getAllMotocycles() {
        return motocycleRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public MotocycleDTO getMotocycleById(Long id) {
        return motocycleRepository.findById(id)
            .map(this::convertToDTO)
            .orElse(null);
    }
}
