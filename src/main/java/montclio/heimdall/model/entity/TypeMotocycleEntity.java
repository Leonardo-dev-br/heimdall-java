package montclio.heimdall.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="TB_HDL_TIPO_MOTO")
public class TypeMotocycleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_TIPO_MOTO")
    private Long idTypeMotocycle;

    @Column(name="MODELO_MOTO")
    private String modelMotocycle;

    @Column(name="COMBUSTAO")
    private Boolean isCombustion;

    public Long getIdTypeMotocycle() {
        return idTypeMotocycle;
    }

    public String getModelMotocycle() {
        return modelMotocycle;
    }

    public void setModelMotocycle(String modelMotocycle) {
        this.modelMotocycle = modelMotocycle;
    }

    public Boolean getIsCombustion() {
        return isCombustion;
    }

    public void setIsCombustion(Boolean isCombustion) {
        this.isCombustion = isCombustion;
    }
    
    
}
