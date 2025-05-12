package montclio.heimdall.model.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="TB_HDL_HISTORICO_MOTO")
public class HistoryMotocycleEntity {
    
    @Column(name="ID_HISTORICO_MOTO")
    private Long idHistoryMotocycle;

    @Column(name="DATA_ATIVIDADE")
    private Date dateActivity;

    @Column(name="ATIVIDADE")
    private String activity;

    public Long getIdHistoryMotocycle() {
        return idHistoryMotocycle;
    }

    public Date getDateActivity() {
        return dateActivity;
    }

    public void setDateActivity(Date dateActivity) {
        this.dateActivity = dateActivity;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    

}
