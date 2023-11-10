package massimomauro.S6L5DevelopmentSpringWebService.repositories;

import massimomauro.S6L5DevelopmentSpringWebService.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DevicesRepository extends JpaRepository<Device, Integer> {
}
