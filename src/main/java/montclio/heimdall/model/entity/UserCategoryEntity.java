package montclio.heimdall.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="TB_HDL_CATEGORIA_USUARIO")
public class UserCategoryEntity {
    @Column(name="ID_CATEGORIA_USUARIO")
    private Long idUserCategory;
    
    @Column(name="CATEGORIA")
    private String category;

    public Long getIdUserCategory() {
        return idUserCategory;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
