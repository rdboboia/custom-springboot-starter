package es.rdboboia.custom.starter.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import es.rdboboia.custom.starter.api.dto.producttype.ProductTypeDto;
import es.rdboboia.custom.starter.api.dto.producttype.ProductTypePostDto;
import es.rdboboia.custom.starter.api.mapper.ProductTypeMapper;
import es.rdboboia.custom.starter.extensions.VerifyNoMoreInteractionsExtension;
import es.rdboboia.custom.starter.persistence.entity.ProductType;
import es.rdboboia.custom.starter.service.ProductTypeService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Test class mainly focused on the API layer since the implementation itself does not have that
 * much happening.
 */
@WebMvcTest(ProductTypeController.class)
@ExtendWith({VerifyNoMoreInteractionsExtension.class})
class ProductTypeControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockitoBean private ProductTypeService productTypeService;
  @MockitoBean private ProductTypeMapper productTypeMapper;

  private final ObjectMapper objectMapper =
      new ObjectMapper()
          .registerModule(new JavaTimeModule())
          .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

  @Test
  void getAllTest() throws Exception {
    // Init
    List<ProductType> productTypes = List.of();
    List<ProductTypeDto> productTypeDtos = List.of();

    // Arrange
    when(this.productTypeService.getAll()).thenReturn(productTypes);
    when(this.productTypeMapper.toDto(productTypes)).thenReturn(productTypeDtos);

    // Act
    String responseContentAsString =
        this.mockMvc
            .perform(get(ProductTypeController.BASE_URL))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

    List<ProductTypeDto> responseContent =
        this.objectMapper.readValue(responseContentAsString, new TypeReference<>() {});

    // Assert
    assertEquals(productTypeDtos, responseContent);

    // Verify
    verify(this.productTypeService).getAll();
    verify(this.productTypeMapper).toDto(productTypes);
  }

  @Test
  void saveTest() throws Exception {
    // Init
    ProductTypePostDto productTypePostDto = ProductTypePostDto.builder().name("Name").build();
    ProductTypeDto productTypeDto = new ProductTypeDto();

    ProductType productType = new ProductType();

    // Arrange
    when(this.productTypeMapper.toEntity(productTypePostDto)).thenReturn(productType);
    when(this.productTypeService.save(productType)).thenReturn(productType);
    when(this.productTypeMapper.toDto(productType)).thenReturn(productTypeDto);

    // Act
    String responseContentAsString =
        this.mockMvc
            .perform(
                post(ProductTypeController.BASE_URL)
                    .content(this.objectMapper.writeValueAsString(productTypePostDto))
                    .contentType("application/json"))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

    ProductTypeDto responseContent =
        this.objectMapper.readValue(responseContentAsString, ProductTypeDto.class);

    // Assert
    assertEquals(productTypeDto, responseContent);

    // Verify
    verify(this.productTypeMapper).toEntity(productTypePostDto);
    verify(this.productTypeService).save(productType);
    verify(this.productTypeMapper).toDto(productType);
  }

  @Test
  void updateTest() throws Exception {
    // Init
    Long id = 1L;
    ProductTypePostDto productTypePostDto = ProductTypePostDto.builder().name("Name").build();
    ProductTypeDto productTypeDto = new ProductTypeDto();

    ProductType productType = new ProductType();

    // Arrange
    when(this.productTypeMapper.toEntity(productTypePostDto)).thenReturn(productType);
    when(this.productTypeService.update(id, productType)).thenReturn(productType);
    when(this.productTypeMapper.toDto(productType)).thenReturn(productTypeDto);

    // Act
    String responseContentAsString =
        this.mockMvc
            .perform(
                patch(ProductTypeController.BASE_URL + ProductTypeController.ID_URL_VARIABLE, id)
                    .content(this.objectMapper.writeValueAsString(productTypePostDto))
                    .contentType("application/json"))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

    ProductTypeDto responseContent =
        this.objectMapper.readValue(responseContentAsString, ProductTypeDto.class);

    // Assert
    assertEquals(productTypeDto, responseContent);

    // Verify
    verify(this.productTypeMapper).toEntity(productTypePostDto);
    verify(this.productTypeService).update(id, productType);
    verify(this.productTypeMapper).toDto(productType);
  }

  @Test
  void deleteTest() throws Exception {
    // Init
    Long id = 1L;

    // Act
    this.mockMvc
        .perform(delete(ProductTypeController.BASE_URL + ProductTypeController.ID_URL_VARIABLE, id))
        .andExpect(status().isOk());

    // Verify
    verify(this.productTypeService).delete(id);
  }
}
