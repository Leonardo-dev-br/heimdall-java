package montclio.heimdall.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import montclio.heimdall.model.Motorcycle;

public interface MotorcycleRepository extends JpaRepository<Motorcycle, Long>{


}
