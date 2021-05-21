package com.copious.training.product;

import com.copious.training.api.v1.ProductController;
import com.copious.training.constants.ProductCategoryEnum;
import com.copious.training.domain.ImmutableSku;
import com.copious.training.domain.Sku;
import com.copious.training.service.ProductService;
import com.copious.training.util.TestUtil;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController controller;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testGetProductsByCategory() throws Exception {
        when(productService.getProducts(ProductCategoryEnum.STYLE_AND_FASHION))
                .thenReturn(getMockProductForCategory());

        MvcResult mvcResult = mockMvc.perform(get("/assignment1/product/by/category")
                .param("category", ProductCategoryEnum.STYLE_AND_FASHION.name())
                .servletPath("/assignment1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

//        GenericResponse<List<Sku>> skus = (GenericResponse<List<Sku>>) TestUtil.convertJsonToPojo(mvcResult.getResponse().getContentAsString(), new TypeReference<GenericResponse<List<Sku>>>() {});

        System.out.println(mvcResult.getResponse().getContentAsString());

    }

    @Test
    public void testValidateProductController() throws Exception {
        when(productService.validateProduct(getValidProduct()))
                .thenReturn(Optional.ofNullable(getValidProduct()));
//                .thenThrow(new InvalidProductException(ExceptionCodeEnum.INVALID_PRODUCT,
//                ExceptionCodeEnum.INVALID_PRODUCT.getMessage(),
//                "Invalid SKU: Sku "
//                        + "1234"
//                        + " should match alphanumeric regular expression ^[a-zA-Z0-9]+$ "
//                        + "Special characters not allowed."));

        MvcResult mvcResult = mockMvc.perform(post("/assignment1/product/")
                .content(TestUtil.convertPojoToJsonString(getValidProduct()))
                .servletPath("/assignment1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testGetProductsWithArrayList() throws Exception {
        when(productService.getProductListFromArrayList())
                .thenReturn(getMockProductForCategory());

        MvcResult mvcResult = mockMvc.perform(get("/assignment1/product/with/arraylist")
                .servletPath("/assignment1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(mvcResult.getResponse().getStatus(), 200);
        System.out.println(mvcResult.getResponse().getContentAsString());

    }

    @Test
    public void testGetProductsWithLinkedList() throws Exception {
        when(productService.getProductListFromLinkedList())
                .thenReturn(getMockProductForCategory());

        MvcResult mvcResult = mockMvc.perform(get("/assignment1/product/with/linkedlist")
                .servletPath("/assignment1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(mvcResult.getResponse().getStatus(), 200);
        System.out.println(mvcResult.getResponse().getContentAsString());

    }

    /**
     * Mocking ValidProduct
     *
     * @return Product
     * @throws IOException
     */
    private Sku getValidProduct() throws IOException, InterruptedException {
        return ImmutableSku
                .builder()
                .name("test sku")
                .sku("1234")
                .category(ProductCategoryEnum.STYLE_AND_FASHION)
                .other("test")
                .productUrl("XYZ")
                .quantity(1)
                .salePrice(new BigDecimal(100))
                .totalPrice(new BigDecimal(100))
                .unitPrice(new BigDecimal(100))
                .imageUrl("test")
                .build();
    }

    /**
     * Mocking Product for STYLE_AND_FASHION category
     *
     * @return Product
     * @throws IOException
     */
    private List<Sku> getMockProductForCategory() {
        return Arrays.asList(ImmutableSku.builder()
                .name("test sku")
                .sku("1234")
                .category(ProductCategoryEnum.STYLE_AND_FASHION)
                .other("test")
                .productUrl("XYZ")
                .quantity(1)
                .salePrice(new BigDecimal(100))
                .totalPrice(new BigDecimal(100))
                .unitPrice(new BigDecimal(100))
                .imageUrl("test")
                .build());
    }
}
