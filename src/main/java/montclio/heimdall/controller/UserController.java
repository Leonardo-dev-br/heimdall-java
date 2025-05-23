package montclio.heimdall.controller;

import jakarta.validation.Valid;
import montclio.heimdall.dto.GetUserDTO;
import montclio.heimdall.dto.PostUserDTO;
import montclio.heimdall.dto.PutUserDTO;
import montclio.heimdall.model.User;
import montclio.heimdall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Listar todos os usuários com paginação
    @GetMapping
    public ResponseEntity<Page<GetUserDTO>> getAllUsers(
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable page) {
        return ResponseEntity.ok(userService.getAllUsers(page));
    }

    // Buscar usuário por ID
    @GetMapping("/{id}")
    public ResponseEntity<GetUserDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    // Cadastrar novo usuário
    @PostMapping
    public ResponseEntity<Void> postUser(@RequestBody @Valid PostUserDTO dto) {
        User savedUser = userService.postUser(dto);
        return ResponseEntity.created(URI.create("/users/"+savedUser.getId())).build();
    }

    // Atualizar usuário
    @PutMapping("/{id}")
    public ResponseEntity<Void> putUser(@RequestBody @Valid PutUserDTO dto, @PathVariable Long id) {
        userService.putUser(id, dto);
        return ResponseEntity.noContent().build();
    }

    // Deletar usuário
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
