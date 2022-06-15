package eu.kiminiuslt.bdsm.history.repository;

import eu.kiminiuslt.bdsm.history.model.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Long> {}
