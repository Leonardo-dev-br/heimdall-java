package montclio.heimdall.specification;

import jakarta.persistence.criteria.Predicate;
import montclio.heimdall.dto.TagRfidFilter;
import montclio.heimdall.model.TagRfId;
import montclio.heimdall.model.fields.TagRfIdFields;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TagRfidSpecification {
    public static Specification<TagRfId> withFilter(TagRfidFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filtro por frequência
            Optional.ofNullable(filter.frequencia())
                    .map(String::toLowerCase)
                    .ifPresent(frequencia -> predicates.add(
                            cb.like(cb.lower(root.get(TagRfIdFields.FREQUENCIA)), "%" + frequencia + "%")
                    ));

            // Filtro por banda
            Optional.ofNullable(filter.banda())
                    .map(String::toLowerCase)
                    .ifPresent(banda -> predicates.add(
                            cb.like(cb.lower(root.get(TagRfIdFields.BANDA)), "%" + banda + "%")
                    ));

            // Filtro por aplicação
            Optional.ofNullable(filter.aplicacao())
                    .map(String::toLowerCase)
                    .ifPresent(aplicacao -> predicates.add(
                            cb.like(cb.lower(root.get(TagRfIdFields.APLICACAO)), "%" + aplicacao + "%")
                    ));

            // Filtro por ID da moto relacionada
            Optional.ofNullable(filter.motorcycleId())
                    .ifPresent(motoId -> predicates.add(
                            cb.equal(root.get(TagRfIdFields.MOTORCYCLE).get("id"), motoId)
                    ));

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
