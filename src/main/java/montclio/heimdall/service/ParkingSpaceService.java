package montclio.heimdall.service;

import jakarta.transaction.Transactional;
import montclio.heimdall.dto.ParkingSpaceDTO.GetParkingSpaceDTO;
import montclio.heimdall.dto.ParkingSpaceDTO.PostParkingSpaceDTO;
import montclio.heimdall.dto.ParkingSpaceDTO.PutParkingSpaceDTO;
import montclio.heimdall.dto.ParkingSpaceDTO.ParkingSpaceFilter;
import montclio.heimdall.exception.ResourceNotFoundException;
import montclio.heimdall.model.ParkingSpace;
import montclio.heimdall.repository.ParkingSpaceRepository;
import montclio.heimdall.specification.ParkingSpaceSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ParkingSpaceService {

    @Autowired
    private ParkingSpaceRepository parkingSpaceRepository;

    public Page<GetParkingSpaceDTO> getAllParkingSpaces(ParkingSpaceFilter filter, Pageable page) {
        Specification<ParkingSpace> spec = ParkingSpaceSpecification.withFilter(filter);
        return parkingSpaceRepository.findAll(spec, page).map(GetParkingSpaceDTO::new);
    }

    @Cacheable(value = "parkingSpaceById", key = "#id")
    public GetParkingSpaceDTO getParkingSpaceById(Long id) {
        return parkingSpaceRepository.findById(id)
                .map(GetParkingSpaceDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException("Vaga com ID " + id + " não encontrada"));
    }

    @Transactional
    @CacheEvict(value = "parkingspaces", allEntries = true)
    public ParkingSpace postParkingSpace(PostParkingSpaceDTO dto) {
        ParkingSpace v = new ParkingSpace(dto);
        return parkingSpaceRepository.save(v);
    }

    @Transactional
    @CacheEvict(value = {"parkingspaces", "parkingSpaceById"}, allEntries = true)
    public void putParkingSpace(Long id, PutParkingSpaceDTO dto) {
        var v = parkingSpaceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vaga com ID " + id + " não encontrada"));
        v.updateData(dto);
    }

    @Transactional
    @CacheEvict(value = {"parkingspaces", "parkingSpaceById"}, allEntries = true)
    public void deleteParkingSpace(Long id) {
        if (!parkingSpaceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Vaga com ID " + id + " não encontrada");
        }
        parkingSpaceRepository.deleteById(id);
    }
}
