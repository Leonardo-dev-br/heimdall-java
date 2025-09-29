package montclio.heimdall.service;

import jakarta.transaction.Transactional;
import montclio.heimdall.dto.HistoryDTO.GetHistoryDTO;
import montclio.heimdall.dto.HistoryDTO.HistoryFilter;
import montclio.heimdall.dto.HistoryDTO.PostHistoryDTO;
import montclio.heimdall.dto.HistoryDTO.PutHistoryDTO;
import montclio.heimdall.exception.ResourceNotFoundException;
import montclio.heimdall.model.HistoryMoto;
import montclio.heimdall.repository.HistoryMotoRepository;
import montclio.heimdall.specification.HistorySpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class HistoryService {

    @Autowired
    private HistoryMotoRepository historicoRepository;

    public Page<GetHistoryDTO> getAllHistoricos(HistoryFilter filter, Pageable page) {
        Specification<HistoryMoto> spec = HistoryMotoRepository.withFilter(filter);
        return historicoRepository.findAll(spec, page).map(GetHistoryDTO::new);
    }

    @Cacheable(value = "historicoById", key = "#id")
    public GetHistoryDTO getHistoricoById(Long id) {
        return historicoRepository.findById(id)
                .map(GetHistoryDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException("Histórico com ID " + id + " não encontrado"));
    }

    @Transactional
    @CacheEvict(value = "historicos", allEntries = true)
    public HistoryMoto postHistorico(PostHistoryDTO dto) {
        HistoryMoto history = new HistoryMoto(dto);
        return historicoRepository.save(history);
    }

    // Atualiza
    @Transactional
    @CacheEvict(value = {"historicos", "historicoById"}, allEntries = true)
    public void putHistorico(Long id, PutHistoryDTO dto) {
        var history = historicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Histórico com ID " + id + " não encontrado"));
        history.updateData(dto);
    }

    // Deleta
    @Transactional
    @CacheEvict(value = {"historicos", "historicoById"}, allEntries = true)
    public void deleteHistorico(Long id) {
        if (!historicoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Histórico com ID " + id + " não encontrado");
        }
        historicoRepository.deleteById(id);
    }
}
