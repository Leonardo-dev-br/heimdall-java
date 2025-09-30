package montclio.heimdall.repository;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.*;

import org.springframework.data.jpa.domain.Specification;

import montclio.heimdall.model.HistoryMoto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import montclio.heimdall.dto.HistoryDTO.HistoryFilter;

public interface HistoryMotoRepository extends JpaRepository<HistoryMoto, Long>, JpaSpecificationExecutor<HistoryMoto> {

    static Specification<HistoryMoto> withFilter(HistoryFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filter == null) return cb.conjunction();

            if (filter.idMoto() != null) {
                predicates.add(cb.equal(root.get("idMoto"), filter.idMoto()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
