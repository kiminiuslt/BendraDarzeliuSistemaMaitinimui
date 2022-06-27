package eu.kiminiuslt.bdsm.core.history;

import eu.kiminiuslt.bdsm.jpa.entity.History;
import eu.kiminiuslt.bdsm.jpa.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class HistoryService {

  private final HistoryRepository historyRepository;

  private Authentication getAuthentication() {
    return SecurityContextHolder.getContext().getAuthentication();
  }

  public void savedWarehouseRecord(String productName, double amount) {
    Authentication auth = getAuthentication();
    historyRepository.save(
        History.builder()
            .name(productName)
            .preformedAction("saved")
            .userPreformedAction(auth.getName())
            .amount(amount)
            .timestamp(LocalDateTime.now())
            .build());
  }
}
