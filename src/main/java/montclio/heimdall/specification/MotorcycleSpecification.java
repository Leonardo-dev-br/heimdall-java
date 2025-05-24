package montclio.heimdall.specification;

import jakarta.persistence.criteria.Predicate;
import montclio.heimdall.dto.MotorcycleDTO.MotorcycleFilter;
import montclio.heimdall.model.Motorcycle;
import montclio.heimdall.constants.MotorcycleFields;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MotorcycleSpecification {

    public static Specification<Motorcycle> withFilter (MotorcycleFilter filter){
        return ((root, query, cb) -> {
            List< Predicate> predicates = new ArrayList<>();

            //Filtro por placa
            Optional.ofNullable(filter.plate())
                    .map(String::toLowerCase)
                    .ifPresent(placa -> predicates.add(
                            cb.like(cb.lower(root.get(MotorcycleFields.PLATE)), "%" + placa + "%")
                    ));

            //Filtro por chassi
            Optional.ofNullable(filter.chassiNumber())
                    .map(String::toLowerCase)
                    .ifPresent(chassiNumber -> predicates.add(
                            cb.like(cb.lower(root.get(MotorcycleFields.CHASSI_NUMBER)), "%" + chassiNumber + "%")
                    ));

            //Filtro por Tipo de moto
            Optional.ofNullable(filter.motorcycleType())
                    .ifPresent(motorcycleType -> predicates.add(
                            cb.like(cb.lower(root.get(MotorcycleFields.MOTORCYCLE_TYPE)), "%" + motorcycleType + "%")
                    ));

            //Buscar por tag (Semelhante a um inner join)
            Optional.ofNullable(filter.tagId())
                    .ifPresent(tagId -> predicates.add(
                            cb.equal(root.join(MotorcycleFields.TAG).get("id"), tagId)
                    ));

            var arrayPredicates = predicates.toArray(new Predicate[0]);
            return cb.and(arrayPredicates);
        });


    }
}
