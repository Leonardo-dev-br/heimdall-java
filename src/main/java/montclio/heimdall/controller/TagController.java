package montclio.heimdall.controller;

import jakarta.transaction.Transactional;
import montclio.heimdall.dto.GetTagRfidDTO;
import montclio.heimdall.dto.PostTagRfidDTO;
import montclio.heimdall.dto.PutTagRfidDTO;
import montclio.heimdall.model.TagRfId;
import montclio.heimdall.service.TagRfidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/tag")
public class TagController {


    @Autowired
    private TagRfidService tagService;

    @GetMapping
    public ResponseEntity<Page<GetTagRfidDTO>> getAllTags(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(tagService.getAllTags(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetTagRfidDTO> getTabById(@PathVariable Long id){
        return ResponseEntity.ok(tagService.getTagById(id));
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Void> putTag(@RequestBody PostTagRfidDTO tagDTO){
        TagRfId tag = new TagRfId(tagDTO);
        tagService.postTag(tagDTO);
        return ResponseEntity.created(URI.create("/tag/"+tag.getId())).build();
    }

    @Transactional
    @PutMapping
    public ResponseEntity<Void> putTag(@RequestBody PutTagRfidDTO tagRfidDTO){
        tagService.putTag(tagRfidDTO);
        return ResponseEntity.ok().build();
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable Long id){
        tagService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }

}
