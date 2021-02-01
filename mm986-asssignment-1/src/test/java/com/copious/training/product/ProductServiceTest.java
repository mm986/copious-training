package com.copious.training.product;

import com.copious.training.api.errors.InvalidProductException;
import com.copious.training.constants.ProductCategoryEnum;
import com.copious.training.domain.ImmutableSku;
import com.copious.training.domain.Sku;
import com.copious.training.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Mahesh More.
 * <p>
 * Test class to test various operations on Products.
 */
@SpringBootTest
@Log4j2
public class ProductServiceTest {

    @InjectMocks
    ProductService productService;

    private static final String EXPENSIVE_PRODUCT = "1112296";

    /**
     * Intended to test GetProductListFromArrayList
     *
     * @throws IOException
     */
    @Test
    public void testGetProductListFromArrayList() throws IOException, InterruptedException {
        assertEquals(2, productService.getProductListFromArrayList().size());
    }

    /**
     * Intended to test GetProductListFromLinkedList
     *
     * @throws IOException
     */
    @Test
    public void testGetProductListFromLinkedList() throws IOException, InterruptedException {
        assertEquals(2, productService.getProductListFromLinkedList().size());
    }

    /**
     * Intended to test GetExpensiveProduct
     *
     * @throws IOException
     */
    @Test
    public void testGetExpensiveProduct() throws IOException, InterruptedException {
        assertEquals(EXPENSIVE_PRODUCT, productService.getExpensiveProduct().getSku());
    }

    /**
     * Intended to test GetProductsByCategory. Implementation of Factory design pattern.
     *
     * @throws IOException
     */
    @Test
    public void testGetProductsByCategory() throws IOException, InterruptedException {
        assertEquals(1, productService.getProducts(ProductCategoryEnum.HEALTH_AND_WELLNESS).size());
        assertEquals(1, productService.getProducts(ProductCategoryEnum.STYLE_AND_FASHION).size());
        assertEquals(0, productService.getProducts(ProductCategoryEnum.APPAREL_AND_ACCESSORIES).size());
        assertEquals(0, productService.getProducts(ProductCategoryEnum.CHILDREN_AND_INFANTS).size());
        assertEquals(0, productService.getProducts(ProductCategoryEnum.CONSUMER_ELECTRONIC_GOODS).size());
        assertEquals(0, productService.getProducts(ProductCategoryEnum.HOME_AND_GARDEN).size());
        assertEquals(0, productService.getProducts(ProductCategoryEnum.HOME_IMPROVEMENT).size());
        assertEquals(0, productService.getProducts(ProductCategoryEnum.MEDICAL_HEALTH).size());
        assertEquals(0, productService.getProducts(ProductCategoryEnum.PETS_AND_PET_SUPPLIES).size());
        assertEquals(0, productService.getProducts(ProductCategoryEnum.SPORTING_GOODS).size());
    }

    /**
     * Intended to test ValidateProduct
     *
     * @throws IOException
     */
    @Test
    public void testValidateProduct() throws IOException, InterruptedException {
        assertEquals(EXPENSIVE_PRODUCT, productService
                .validateProduct(getValidProduct())
                .map(Sku::getSku)
                .orElse("")
        );
    }

    /**
     * Intended to test ProductWithInvalidName.
     *
     * @throws IOException
     */
    @Test
    public void testProductWithInvalidName() throws IOException {
        try {
            productService.validateProduct(getProductWithInvalidName());
        } catch (InvalidProductException | InterruptedException e) {
            log.info("Test case testProductWithInvalidName pass. Caught {} :: {}",
                    e.getClass(),
                    e.getMessage()
            );
        }
    }

    /**
     * Intended to test ProductWithInvalidPrice.
     *
     * @throws IOException
     */
    @Test
    public void testProductWithInvalidPrice() throws IOException {
        try {
            productService.validateProduct(getProductWithInvalidPrice());
        } catch (InvalidProductException | InterruptedException e) {
            log.info("Test case testProductWithInvalidPrice pass. Caught {} :: {}",
                    e.getClass(),
                    e.getMessage()
            );
        }
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
                .from(productService.getExpensiveProduct())
                .build();
    }

    /**
     * Mocking ProductWithInvalidPrice
     *
     * @return Product
     * @throws IOException
     */
    private Sku getProductWithInvalidPrice() throws IOException, InterruptedException {
        return ImmutableSku
                .builder()
                .from(productService.getExpensiveProduct())
                .totalPrice(BigDecimal.valueOf(-1))
                .build();
    }

    /**
     * Mocking ProductWithInvalidName
     *
     * @return Product
     * @throws IOException
     */
    private Sku getProductWithInvalidName() throws IOException, InterruptedException {
        return ImmutableSku
                .builder()
                .from(productService.getExpensiveProduct())
                .sku("ABC-D1234")
                .build();
    }
}
