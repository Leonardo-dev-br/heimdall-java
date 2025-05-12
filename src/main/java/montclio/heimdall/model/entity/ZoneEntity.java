package montclio.heimdall.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="TB_HDL_ZONA")
public class ZoneEntity {
    @Column(name="ID_ZONA")
    private Long idZone;

    @Column(name="ZONA")
    private String zone;

    @Column(name="COD_ZONA")
    private String codeZone;

    public Long getIdZone() {
        return idZone;
    }


    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }


    public String getCodeZone() {
        return codeZone;
    }

    public void setCodeZone(String codeZone) {
        this.codeZone = codeZone;
    }


}
