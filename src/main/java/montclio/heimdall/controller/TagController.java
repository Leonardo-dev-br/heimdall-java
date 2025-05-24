package montclio.heimdall.controller;

import jakarta.validation.Valid;
import montclio.heimdall.dto.TagRfidDTO.GetTagRfidDTO;
import montclio.heimdall.dto.TagRfidDTO.PostTagRfidDTO;
import montclio.heimdall.dto.TagRfidDTO.PutTagRfidDTO;
import montclio.heimdall.dto.TagRfidDTO.TagRfidFilter;
import montclio.heimdall.model.TagRfId;
import montclio.heimdall.service.TagRfidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/tags")
public class TagController {


    @Autowired
    private TagRfidService tagService;

    @GetMapping
    public ResponseEntity<Page<GetTagRfidDTO>> getAllTags(TagRfidFilter filter, @PageableDefault (size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable page){
        return ResponseEntity.ok(tagService.getAllTags(filter, page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetTagRfidDTO> getTagById(@PathVariable Long id){
        return ResponseEntity.ok(tagService.getTagById(id));
    }

    @PostMapping
    public ResponseEntity<Void> createTag(@Valid @RequestBody PostTagRfidDTO tagDTO){
        TagRfId savedTagRfid = tagService.postTag(tagDTO);
        return ResponseEntity.created(URI.create("/tags/"+savedTagRfid.getId())).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTag(@PathVariable Long id, @Valid @RequestBody PutTagRfidDTO tagRfidDTO){
        tagService.putTag(id, tagRfidDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTagById(@PathVariable Long id){
        tagService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }

}
