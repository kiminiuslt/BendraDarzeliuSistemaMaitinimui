package eu.kiminiuslt.bdsm.unit.product;

import eu.kiminiuslt.bdsm.core.helpers.MessageService;
import eu.kiminiuslt.bdsm.core.product.controller.ProductController;
import eu.kiminiuslt.bdsm.core.product.model.dto.ProductDto;
import eu.kiminiuslt.bdsm.core.product.service.ProductService;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

  private MockMvc mockMvc;

  @Mock private ProductService productService;

  @Mock private MessageService messageService;

  @InjectMocks private ProductController productController;

  @BeforeEach
  void setUp() {
    mockMvc =
        MockMvcBuilders.standaloneSetup(productController)
            .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
            .build();
  }

  @Test
  void getProducts() throws Exception {
    Page<ProductDto> pageableProduct = new PageImpl<>(List.of(ProductMother.getProductDto()));
    String givenMessage = "";
    when(messageService.getMessage(null)).thenReturn(givenMessage);
    when(productService.getPageableProduct(any(Pageable.class))).thenReturn(pageableProduct);
    mockMvc
        .perform(MockMvcRequestBuilders.get("/products").param("size", "1"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("/products/products"))
        .andExpect(MockMvcResultMatchers.model().attribute("productListPages", pageableProduct))
        .andExpect(MockMvcResultMatchers.model().attribute("message", givenMessage));
  }

  @Test
  void openProductForm() throws Exception {
    ProductDto givenProductDto = ProductDto.builder().build();
    when(productService.getEmptyProductDto()).thenReturn(givenProductDto);
    mockMvc
        .perform(MockMvcRequestBuilders.get("/products/productForm"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("products/product-form"))
        .andExpect(MockMvcResultMatchers.model().attribute("productDto", givenProductDto));
  }

  @Test
  void saveProduct_WhenProductDtoIsValid() throws Exception {
    ProductDto productDto = ProductMother.getProductDto();
    RequestBuilder request = post("/products/productForm").flashAttr("productDto", productDto);
    mockMvc
        .perform(request)
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
        .andExpect(
            MockMvcResultMatchers.view()
                .name("redirect:/products?message=product.create.successes"));
  }

  @Test
  void saveProduct_WhenProductDtoIsNotValid() throws Exception {
    ProductDto productDto = ProductMother.getProductDto();
    productDto.setName("");
    RequestBuilder request = post("/products/productForm").flashAttr("productDto", productDto);
    mockMvc
        .perform(request)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("products/product-form"));
  }

  @Test
  void getUpdateProduct_WhenProductExist() throws Exception {
    ProductDto givenProductDto = ProductMother.getProductDto();
    UUID givenUUID = givenProductDto.getUuid();
    when(productService.getProductDtoByUUID(givenUUID)).thenReturn(givenProductDto);
    mockMvc
        .perform(MockMvcRequestBuilders.get("/products/" + givenUUID + "/update"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("products/product-form"))
        .andExpect(MockMvcResultMatchers.model().attribute("productDto", givenProductDto));
  }

  @Test
  void updateProduct_WhenProductUpdated() throws Exception {
    UUID givenUUID = UUID.fromString("cc4038b9-d4a6-4830-8470-ac2bb5207a18");
    mockMvc
        .perform(post("/products/" + givenUUID + "/update"))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
        .andExpect(
            MockMvcResultMatchers.view()
                .name("redirect:/products?message=product.update.successes"));
  }

  @Test
  void updateProduct_WhenProductNotUpdated() throws Exception {
    mockMvc
        .perform(post("/products/" + "" + "/update"))
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  void deleteProduct_WhenProductDeleted() throws Exception {
    UUID givenUUID = UUID.fromString("cc4038b9-d4a6-4830-8470-ac2bb5207a18");
    mockMvc
        .perform(MockMvcRequestBuilders.get("/products/" + givenUUID + "/delete"))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
        .andExpect(
            MockMvcResultMatchers.view()
                .name("redirect:/products?message=product.delete.successes"));
  }

  @Test
  void deleteProduct_WhenProductNotDeleted() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.get("/products/" + "" + "/delete"))
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }
}
