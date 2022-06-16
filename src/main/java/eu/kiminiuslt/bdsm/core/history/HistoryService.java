package eu.kiminiuslt.bdsm.core.history;

import eu.kiminiuslt.bdsm.jpa.entity.History;
import eu.kiminiuslt.bdsm.jpa.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class HistoryService {

  private final HistoryRepository historyRepository;

  public void createRecord() {
    historyRepository.save(
        History.builder()
            .name("Sviestas")
            .preformedAction("saved")
            .userPreformedAction("admin")
            .timestamp(LocalDateTime.now())
            .build());
  }
}
