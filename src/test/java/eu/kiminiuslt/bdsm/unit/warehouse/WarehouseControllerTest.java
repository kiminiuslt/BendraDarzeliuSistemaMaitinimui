package eu.kiminiuslt.bdsm.unit.warehouse;

import eu.kiminiuslt.bdsm.core.helpers.MessageService;
import eu.kiminiuslt.bdsm.core.product.model.dto.ProductsNamesDto;
import eu.kiminiuslt.bdsm.unit.product.ProductMother;
import eu.kiminiuslt.bdsm.core.warehouse.controllers.WarehouseController;
import eu.kiminiuslt.bdsm.core.warehouse.model.dto.WarehouseDto;
import eu.kiminiuslt.bdsm.core.warehouse.service.WarehouseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
class WarehouseControllerTest {

  private MockMvc mockMvc;

  @Mock private WarehouseService warehouseService;

  @Mock private MessageService messageService;

  @InjectMocks private WarehouseController warehouseController;

  @BeforeEach
  void setUp() {
    mockMvc =
        MockMvcBuilders.standaloneSetup(warehouseController)
            .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
            .build();
  }

  @Test
  void warehouseStart() throws Exception {
    Page<WarehouseDto> pageableWarehouse =
        new PageImpl<>(List.of(WarehouseMother.getWarehouseDto()));
    String givenMessage = "";
    when(messageService.getMessage(null)).thenReturn(givenMessage);
    when(warehouseService.getWarehouseList(any(Pageable.class))).thenReturn(pageableWarehouse);
    mockMvc
        .perform(MockMvcRequestBuilders.get("/warehouse").param("size", "1"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("/warehouse/warehouse-all"))
        .andExpect(MockMvcResultMatchers.model().attribute("warehousePages", pageableWarehouse))
        .andExpect(MockMvcResultMatchers.model().attribute("message", givenMessage));
    verify(messageService, times(1)).getMessage(null);
    verify(warehouseService, times(1)).getWarehouseList(any(Pageable.class));
  }

  @Test
  void newWarehouseRecord() throws Exception {
    WarehouseDto given = WarehouseDto.builder().build();
    List<ProductsNamesDto> givenList = List.of(ProductMother.getProductsNamesDto());
    String givenMessage = "";
    when(messageService.getMessage(null)).thenReturn(givenMessage);
    when(warehouseService.getEmptyWarehouseDto()).thenReturn(given);
    when(warehouseService.getAllProductsNames()).thenReturn(givenList);
    mockMvc
        .perform(MockMvcRequestBuilders.get("/warehouse/warehouse-record"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("warehouse/warehouse-record"))
        .andExpect(MockMvcResultMatchers.model().attribute("warehouseDto", given))
        .andExpect(MockMvcResultMatchers.model().attribute("allProductsList", givenList))
        .andExpect(MockMvcResultMatchers.model().attribute("message", givenMessage));
    verify(warehouseService, times(1)).getEmptyWarehouseDto();
    verify(warehouseService, times(1)).getAllProductsNames();
    verify(messageService, times(1)).getMessage(null);
  }

  @Test
  void saveWarehouseRecord_WhenWarehouseDtoValid() throws Exception {
    WarehouseDto given = WarehouseMother.getWarehouseDto();
    RequestBuilder request = post("/warehouse/warehouse-record").flashAttr("warehouseDto", given);
    mockMvc
        .perform(request)
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
        .andExpect(
            MockMvcResultMatchers.view()
                .name(
                    "redirect:/warehouse/warehouse-record?message=warehouse.create.success.message"));
    verify(warehouseService, times(1)).addWarehouseRecord(given);
  }

  @Test
  void saveWarehouseRecord_WhenWarehouseDtoNotValid() throws Exception {
    WarehouseDto given = WarehouseDto.builder().build();
    RequestBuilder request = post("/warehouse/warehouse-record").flashAttr("warehouseDto", given);
    mockMvc
        .perform(request)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("warehouse/warehouse-record"));
  }

  @Test
  void updateWarehouse() throws Exception {
    WarehouseDto given = WarehouseMother.getWarehouseDto();
    UUID uuid = given.getUuid();
    when(warehouseService.getWarehouseDtoRecordByUUID(uuid)).thenReturn(given);
    mockMvc
        .perform(MockMvcRequestBuilders.get("/warehouse/" + uuid + "/update"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("/warehouse/warehouse-record"))
        .andExpect(MockMvcResultMatchers.model().attribute("warehouseDto", given));
    verify(warehouseService, times(1)).getWarehouseDtoRecordByUUID(uuid);
  }

  @Test
  void testUpdateWarehouse() throws Exception {
    WarehouseDto given = WarehouseMother.getWarehouseDto();
    UUID uuid = given.getUuid();
    RequestBuilder request =
        post("/warehouse/" + uuid + "/update").flashAttr("warehouseDto", given);
    mockMvc
        .perform(request)
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
        .andExpect(
            MockMvcResultMatchers.view()
                .name("redirect:/warehouse?message=warehouse.update.successes"));
    verify(warehouseService, times(1)).updateWarehouse(given);
  }

  @Test
  void deleteWarehouse() throws Exception {
    WarehouseDto given = WarehouseMother.getWarehouseDto();
    UUID uuid = given.getUuid();
    mockMvc
        .perform(MockMvcRequestBuilders.get("/warehouse/" + uuid + "/delete"))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
        .andExpect(
            MockMvcResultMatchers.view()
                .name("redirect:/warehouse?message=warehouse.delete.successes"));
    verify(warehouseService, times(1)).deleteWarehouseRecord(uuid);
  }

  @Test
  void writeOff() throws Exception {
    WarehouseDto given = WarehouseMother.getWarehouseDto();
    UUID uuid = given.getUuid();
    when(warehouseService.getWarehouseDtoRecordByUUID(uuid)).thenReturn(given);
    mockMvc
        .perform(MockMvcRequestBuilders.get("/warehouse/" + uuid + "/writeOff"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("/warehouse/write-off"))
        .andExpect(MockMvcResultMatchers.model().attribute("warehouseDto", given));
    verify(warehouseService, times(1)).getWarehouseDtoRecordByUUID(uuid);
  }

  @Test
  void testWriteOff_WhenWarehouseDtoValid() throws Exception {
    WarehouseDto given = WarehouseMother.getWarehouseDto();
    UUID uuid = given.getUuid();
    RequestBuilder request =
        post("/warehouse/" + uuid + "/writeOff").flashAttr("warehouseDto", given);
    mockMvc
        .perform(request)
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
        .andExpect(
            MockMvcResultMatchers.view()
                .name("redirect:/warehouse?message=warehouse.write.off.successes"));
    verify(warehouseService, times(1)).writeOff(given.getWriteOff(), uuid);
  }

  @Test
  void testWriteOff_WhenWarehouseDtoNotValid() throws Exception {
    WarehouseDto given = WarehouseMother.getWarehouseDto();
    WarehouseDto givenNotValid = WarehouseDto.builder().build();
    UUID uuid = given.getUuid();
    RequestBuilder request =
        post("/warehouse/" + uuid + "/writeOff").flashAttr("warehouseDto", givenNotValid);
    mockMvc
        .perform(request)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("/warehouse/write-off"));
  }
}
