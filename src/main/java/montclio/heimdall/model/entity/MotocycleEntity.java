package montclio.heimdall.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="TB_HDL_MOTO")
public class MotocycleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_MOTO")
    private Long idMotocycle;

    @Column(name="PLACA")
    private String plate;

    @Column(name="NUM_CHASSI")
    private String chassiNumber;

    public Long getIdMotocycle() {
        return idMotocycle;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getChassiNumber() {
        return chassiNumber;
    }

    public void setChassiNumber(String chassiNumber) {
        this.chassiNumber = chassiNumber;
    }



}
