package montclio.heimdall.specification;

import jakarta.persistence.criteria.Predicate;
import montclio.heimdall.dto.UserFilter;
import montclio.heimdall.model.User;
import montclio.heimdall.model.fields.UserFields;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserSpecification {
    public static Specification<User> withFilter (UserFilter filter){
        return ((root, query, cb)  -> {
            List<Predicate> predicates = new ArrayList<>();

            //Filtro por nome
            Optional.ofNullable(filter.name())
                    .map(String::toLowerCase)
                    .ifPresent(name -> predicates.add(
                            cb.like(cb.lower(root.get(UserFields.NAME)), "%" + name + "%")
                    ));

            //Filtro por sobrenome
            Optional.ofNullable(filter.middleName())
                    .map(String::toLowerCase)
                    .ifPresent(middleName -> predicates.add(
                            cb.like(cb.lower(root.get(UserFields.MIDDLE_NAME)), "%" + middleName + "%")
                    ));

            //Filtra por email
            Optional.ofNullable(filter.email())
                    .map(String::toLowerCase)
                    .ifPresent(email -> predicates.add(
                            cb.like(cb.lower(root.get(UserFields.EMAIL)), "%"+ email +"%")
                    ));

            //Filtra por CPF
            Optional.ofNullable(filter.cpf())
                    .map(String::toLowerCase)
                    .ifPresent(cpf-> predicates.add(
                            cb.like(cb.lower(root.get(UserFields.CPF)), "%"+cpf+"%")
                    ));


            //Filtra por UserCategory
            Optional.ofNullable(filter.userCategoryId()).ifPresent(categoryId -> {
                predicates.add(cb.equal(root.get("userCategory").get("id"), categoryId));
            });



            var arrayPredicates = predicates.toArray(new Predicate[0]);
            return cb.and(arrayPredicates);

        });
    }
}
