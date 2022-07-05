package eu.kiminiuslt.bdsm.unit.history;

import eu.kiminiuslt.bdsm.core.history.HistoryService;
import eu.kiminiuslt.bdsm.jpa.entity.History;
import eu.kiminiuslt.bdsm.jpa.repository.HistoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HistoryServiceTest {

  @Mock private HistoryRepository historyRepository;

  @InjectMocks private HistoryService historyService;

  @Test
  void savedWarehouseRecord() {
    Authentication authentication = mock(Authentication.class);
    SecurityContext securityContext = mock(SecurityContext.class);
    SecurityContextHolder.setContext(securityContext);
    when(securityContext.getAuthentication()).thenReturn(authentication);
    when(authentication.getName()).thenReturn("admin");
    historyService.historySavedWarehouseRecord("Yogurt", 15.0);
    verify(historyRepository, times(1)).save(any(History.class));
  }

  @Test
  void deletedWarehouseRecord() {
    Authentication authentication = mock(Authentication.class);
    SecurityContext securityContext = mock(SecurityContext.class);
    SecurityContextHolder.setContext(securityContext);
    when(securityContext.getAuthentication()).thenReturn(authentication);
    when(authentication.getName()).thenReturn("admin");
    historyService.historyDeletedWarehouseRecord("Yogurt");
    verify(historyRepository, times(1)).save(any(History.class));
  }

  @Test
  void updatedWarehouseRecord() {
    Authentication authentication = mock(Authentication.class);
    SecurityContext securityContext = mock(SecurityContext.class);
    SecurityContextHolder.setContext(securityContext);
    when(securityContext.getAuthentication()).thenReturn(authentication);
    when(authentication.getName()).thenReturn("admin");
    historyService.historyUpdatedWarehouseRecord("Yogurt", 15.0);
    verify(historyRepository, times(1)).save(any(History.class));
  }
}
