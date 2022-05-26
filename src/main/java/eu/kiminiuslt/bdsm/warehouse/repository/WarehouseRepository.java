package eu.kiminiuslt.bdsm.warehouse.repository;

import eu.kiminiuslt.bdsm.warehouse.model.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {

  Warehouse findByUuid(UUID uuid);
}
