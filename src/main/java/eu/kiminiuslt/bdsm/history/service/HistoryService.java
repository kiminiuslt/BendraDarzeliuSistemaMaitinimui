package eu.kiminiuslt.bdsm.history.service;

import eu.kiminiuslt.bdsm.history.model.entity.History;
import eu.kiminiuslt.bdsm.history.repository.HistoryRepository;
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
