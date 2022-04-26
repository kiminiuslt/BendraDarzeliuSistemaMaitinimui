package eu.kiminiuslt.bdsm.mock;

import eu.kiminiuslt.bdsm.model.Product;
import eu.kiminiuslt.bdsm.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Log4j2
public class MockDataConfig {
  private static final int MAX_COUNT = 10;
  private final ProductRepository productRepository;

  @Bean
  public void initProducts() {
    var count = 0;
    while (MAX_COUNT >= count) {
      productRepository.save(
          Product.builder().name("duona" + count).carbs(count).kcal(count * 2).build());

      count++;
    }
    log.atDebug().log("=========initialized============");
  }
}
