package montclio.heimdall.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="TB_HDL_TAG_IDENTIFICACAO")
public class TagIdEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_TAG_RFID")
    private Long idTagId;

    
    @Column(name="FAIXA_FREQUENCIA")
    private String frequencyBands;

    
    @Column(name="BANDA")
    private String band;

    
    @Column(name="ALCANCE")
    private float range;

    
    @Column(name="APLICACAO")
    private String aplication;

    public Long getIdTagId() {
        return idTagId;
    }

    public String getFrequencyBands() {
        return frequencyBands;
    }

    public void setFrequencyBands(String frequencyBands) {
        this.frequencyBands = frequencyBands;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public float getRange() {
        return range;
    }

    public void setRange(float range) {
        this.range = range;
    }

    public String getAplication() {
        return aplication;
    }

    public void setAplication(String aplication) {
        this.aplication = aplication;
    }


}
