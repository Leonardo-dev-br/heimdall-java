package montclio.heimdall.service;

import jakarta.transaction.Transactional;
import montclio.heimdall.dto.UserDTO.GetUserDTO;
import montclio.heimdall.dto.UserDTO.PostUserDTO;
import montclio.heimdall.dto.UserDTO.PutUserDTO;
import montclio.heimdall.dto.UserDTO.UserFilter;
import montclio.heimdall.exception.DataConflictException;
import montclio.heimdall.exception.ResourceNotFoundException;
import montclio.heimdall.model.User;
import montclio.heimdall.model.UserCategory;
import montclio.heimdall.repository.UserCategoryRepository;
import montclio.heimdall.repository.UserRepository;
import montclio.heimdall.specification.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;




@Service
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCategoryRepository categoryRepository;


    public Page<GetUserDTO> getAllUsers(UserFilter filter, Pageable page) {
        Specification<User> spec = UserSpecification.withFilter(filter);
        return userRepository.findAll(spec, page).map(GetUserDTO::new);
    }

    // Retorna usuário por ID
    @Cacheable(value = "userById", key = "#id")
    public GetUserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(GetUserDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario com ID " + id + " não encontrado"));
    }

    // Cadastra novo usuário
    @Transactional
    @CacheEvict(value = "users", allEntries = true)
    public User postUser(PostUserDTO dto) {

        boolean exist = userRepository.existsByEmail(dto.email());
        if (exist){
            throw new DataConflictException("Esse e-mail já está sendo usado por outro usuário");
        }

        if (userRepository.existsByCpf(dto.cpf())) {
            throw new DataConflictException("Esse CPF ja esta cadastrado");
        }

        UserCategory category = categoryRepository.findById(dto.userCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria com id: " + dto.userCategoryId() + " não encontrada."));
        User user = new User();
        user.setName(dto.name());
        user.setMiddleName(dto.middleName());
        user.setBirthday(dto.birthday());
        user.setCpf(dto.cpf());
        user.setEmail(dto.email());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setUserCategory(category);
        return userRepository.save(user);
    }

    // Atualiza usuário
    @Transactional
    @CacheEvict(value = {"users", "userById"}, allEntries = true)
    public void putUser(Long id, PutUserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Usuario com ID " + id + " não encontrado"));
        if (userRepository.existsByEmailAndIdNot(userDTO.email(), id)) {
            throw new DataConflictException("Esse e-mail já está sendo usado por outro usuário");
        }
        if (userRepository.existsByCpfAndIdNot(userDTO.cpf(), id)) {
            throw new DataConflictException("Esse CPF ja esta cadastrado");
        }

        user.updateData(userDTO);
    }


    // Deleta usuário
    @Transactional
    @CacheEvict(value = {"users", "userById"}, allEntries = true)
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario com ID " + id + " não encontrado"));
        userRepository.delete(user);
    }
}
