package eu.kiminiuslt.bdsm.core.history;

import eu.kiminiuslt.bdsm.jpa.entity.History;
import eu.kiminiuslt.bdsm.jpa.entity.Product;
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

  public void historySavedWarehouseRecord(String productName, double amount) {
    historyRepository.save(getPreBuildHistory("Įrašyta", productName, amount));
  }

  public void historyDeletedWarehouseRecord(Product product) {
    historyRepository.save(getPreBuildHistory("Ištrinta", product.getName(), 0.0));
  }

  public void historyUpdatedWarehouseRecord(String productName, double amount) {
    historyRepository.save(getPreBuildHistory("Atnaujinta", productName, amount));
  }

  private History getPreBuildHistory(String action, String name, double amount) {
    Authentication auth = getAuthentication();
    return History.builder()
        .name(name)
        .preformedAction(action)
        .userPreformedAction(auth.getName())
        .amount(amount)
        .timestamp(LocalDateTime.now())
        .build();
  }

  private Authentication getAuthentication() {
    return SecurityContextHolder.getContext().getAuthentication();
  }
}
