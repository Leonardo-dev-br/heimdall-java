package montclio.heimdall.service;

import jakarta.transaction.Transactional;
import montclio.heimdall.dto.GetTagRfidDTO;
import montclio.heimdall.dto.PostTagRfidDTO;
import montclio.heimdall.dto.PutTagRfidDTO;
import montclio.heimdall.dto.TagRfidFilter;
import montclio.heimdall.exception.DataConflictException;
import montclio.heimdall.exception.ResourceNotFoundException;
import montclio.heimdall.model.Motorcycle;
import montclio.heimdall.model.TagRfId;
import montclio.heimdall.repository.TagRfidRepository;
import montclio.heimdall.specification.TagRfidSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service
public class TagRfidService {

@Autowired
    private TagRfidRepository tagRfidRepository;

@Autowired
private CacheManager cacheManager;

    //retorna todas as tags cadastradas
    public Page<GetTagRfidDTO> getAllTags(TagRfidFilter filter, Pageable page){
        Specification<TagRfId> spec = TagRfidSpecification.withFilter(filter);
        return tagRfidRepository.findAll(spec, page).map(GetTagRfidDTO::new);
    }

    //retorna tag por id
    @Cacheable(value = "tagById", key = "#id")
    public GetTagRfidDTO getTagById(Long id){
        return tagRfidRepository.findById(id).map(GetTagRfidDTO::new)
                .orElseThrow(()-> new ResourceNotFoundException("Tag com ID " + id + " não encontrada"));
    }

    //Cadastra nova tag
    @Transactional
    @CacheEvict(value = "tags", allEntries = true)
    public TagRfId postTag(PostTagRfidDTO tagDTO){
        boolean exists = tagRfidRepository.existsByMotorcycleId(tagDTO.motorcycle().getId());
        if (exists) {
            throw new DataConflictException("A tag para esta motocicleta já existe.");
        }

        TagRfId tagRfid = new TagRfId(tagDTO);
        return tagRfidRepository.save(tagRfid);
    }


    @Transactional
    @CacheEvict(value = {"tags", "tagById"}, allEntries = true)
    public void putTag(Long id, PutTagRfidDTO tagDTO){
        var tag = tagRfidRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tag com ID " + id + " não encontrada"));

        Motorcycle oldMoto = tag.getMotorcycle();
        tag.updateData(tagDTO); // Aqui é onde a nova moto é ligada

        if (oldMoto != null) {
            cacheManager.getCache("motorcycleById").evict(oldMoto.getId());
            cacheManager.getCache("motorcycles").clear();
        }

        Motorcycle newMoto = tag.getMotorcycle();
        if (newMoto != null) {
            cacheManager.getCache("motorcycleById").evict(newMoto.getId());
            cacheManager.getCache("motorcycles").clear();
        }
    }




    //Deleta uma tag
    @Transactional
    @CacheEvict(value = {"tags", "tagById"}, allEntries = true)
    public void deleteTag (Long id) {
        TagRfId tag =  tagRfidRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Tag com ID " + id + " não encontrada"));
        Motorcycle moto = tag.getMotorcycle();
        if (moto != null){
            moto.setTag(null);
        }
        tag.setMotorcycle(null);
        tagRfidRepository.deleteById(id);
    }



}
