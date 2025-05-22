package montclio.heimdall.service;

import montclio.heimdall.dto.GetTagRfidDTO;
import montclio.heimdall.dto.PostTagRfidDTO;
import montclio.heimdall.dto.PutMotorcycleDTO;
import montclio.heimdall.dto.PutTagRfidDTO;
import montclio.heimdall.model.Motorcycle;
import montclio.heimdall.model.TagRfId;
import montclio.heimdall.repository.TagRfidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class TagRfidService {

@Autowired
    private TagRfidRepository tagRfidRepository;


    //retorna todas as tags cadastradas
    public Page<GetTagRfidDTO> getAllTags(Pageable page){
        return tagRfidRepository.findAll(page).map(GetTagRfidDTO::new);
    }

    //retorna tag por id
    public GetTagRfidDTO getTagById(Long id){
        return tagRfidRepository.findById(id).map(GetTagRfidDTO::new)
                .orElseThrow(()-> new RuntimeException("Tag nao encontrada"));
    }

    //Cadastra nova tag
    public void postTag(PostTagRfidDTO tagDTO){
        TagRfId tagRfid = new TagRfId(tagDTO);
        tagRfidRepository.save(tagRfid);
    }


    //Atualiza tag
    public void putTag(PutTagRfidDTO tagDTO){
        var tag = tagRfidRepository.getReferenceById(tagDTO.id());
        tag.updateData(tagDTO);
    }

    //Deleta uma tag
    public void deleteTag (Long id) {
        TagRfId tag =  tagRfidRepository.findById(id).orElseThrow(()-> new RuntimeException("Tag nao encontrada"));
        Motorcycle moto = tag.getMotorcycle();
        if (moto != null){
            moto.setTag(null);
        }
        tag.setMotorcycle(null);
        tagRfidRepository.deleteById(id);
    }



}
