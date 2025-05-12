package montclio.heimdall.model.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="TB_HDL_USUARIO")
public class UserEntity {
    
    @Column(name="ID_USUARIO")
    private Long idUser;
    
    @Column(name="NOME_USUARIO")
    private String Username;
    
    @Column(name="SOBRENOME")
    private String lastName;
    
    @Column(name="DATA_NASCIMENTO")
    private Date birthDate;
    
    @Column(name="CPF")
    private int cpf;
    
    @Column(name="EMAIL")
    private String email;
    
    @Column(name="SENHA")
    private String password;

    public Long getIdUser() {
        return idUser;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
