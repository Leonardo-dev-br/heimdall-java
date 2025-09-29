package montclio.heimdall.specification;

import jakarta.persistence.criteria.Predicate;
import montclio.heimdall.constants.ParkingSpaceFields;
import montclio.heimdall.dto.ParkingSpaceDTO.ParkingSpaceFilter;
import montclio.heimdall.model.ParkingSpace;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingSpaceSpecification {

    public static Specification<ParkingSpace> withFilter(ParkingSpaceFilter filter) {
        return ((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            Optional.ofNullable(filter.idZona())
                    .ifPresent(idZona -> predicates.add(cb.equal(root.get(ParkingSpaceFields.ID_ZONA), idZona)));

            Optional.ofNullable(filter.preenchida())
                    .ifPresent(preenchida -> predicates.add(cb.equal(root.get(ParkingSpaceFields.PREENCHIDA), preenchida ? 1 : 0)));

            Optional.ofNullable(filter.codVaga())
                    .map(String::toLowerCase)
                    .ifPresent(code -> predicates.add(cb.like(cb.lower(root.get(ParkingSpaceFields.COD_VAGA)), "%" + code + "%")));

            var array = predicates.toArray(new Predicate[0]);
            return cb.and(array);
        });
    }
}
