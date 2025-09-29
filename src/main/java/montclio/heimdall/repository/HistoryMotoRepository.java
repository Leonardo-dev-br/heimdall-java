package montclio.heimdall.repository;

import montclio.heimdall.model.HistoryMoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HistoryMotoRepository extends JpaRepository<HistoryMoto, Long>, JpaSpecificationExecutor<HistoryMoto> {
}