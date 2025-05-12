package montclio.heimdall.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="TB_HDL_VAGA")
public class SpotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_VAGA")
    private Long idSpot;
    
    @Column(name="COD_VAGA")
    private String CodSpot;
    
    @Column(name="PREENCHIDA")
    private Boolean isFilled;

    public Long getIdSpot() {
        return idSpot;
    }

    public String getCodSpot() {
        return CodSpot;
    }

    public void setCodSpot(String codSpot) {
        CodSpot = codSpot;
    }

    public Boolean getIsFilled() {
        return isFilled;
    }

    public void setIsFilled(Boolean isFilled) {
        this.isFilled = isFilled;
    }

}
