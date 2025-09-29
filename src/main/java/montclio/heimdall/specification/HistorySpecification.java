package montclio.heimdall.specification;

import jakarta.persistence.criteria.Predicate;
import montclio.heimdall.constants.HistoryFields;
import montclio.heimdall.dto.HistoryDTO.HistoryFilter;
import montclio.heimdall.model.HistoryMoto;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HistorySpecification {

    public static Specification<HistoryMoto> withFilter(HistoryFilter filter) {
        return ((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            Optional.ofNullable(filter.idMoto())
                    .ifPresent(idMoto -> predicates.add(cb.equal(root.get(HistoryFields.ID_MOTO), idMoto)));

            Optional.ofNullable(filter.atividade())
                    .map(String::toLowerCase)
                    .ifPresent(atividade -> predicates.add(
                            cb.like(cb.lower(root.get(HistoryFields.ATIVIDADE)), "%" + atividade + "%")
                    ));

            if (filter.startDate() != null && filter.endDate() != null) {
                predicates.add(cb.between(root.get(HistoryFields.DATA_ATIVIDADE), filter.startDate(), filter.endDate()));
            } else if (filter.startDate() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get(HistoryFields.DATA_ATIVIDADE), filter.startDate()));
            } else if (filter.endDate() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get(HistoryFields.DATA_ATIVIDADE), filter.endDate()));
            }

            var array = predicates.toArray(new Predicate[0]);
            return cb.and(array);
        });
    }
}
