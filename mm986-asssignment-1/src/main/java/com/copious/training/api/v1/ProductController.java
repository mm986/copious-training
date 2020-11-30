package com.copious.training.api.v1;

import com.copious.training.constants.ProductCategory;
import com.copious.training.domain.Sku;
import com.copious.training.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * @author Mahesh More
 * <p>
 * Controller class to perform CRUD on available Products for Order
 */
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    /**
     * API to get Products by Product Category
     *
     * @return Sku's
     * @throws IOException
     */
    @GetMapping(value = {"/products"})
    List<Sku> getProductsByCategory(
            @RequestParam(value = "category", required = true) ProductCategory category)
            throws IOException {
        return productService.getProducts(category);
    }

    /**
     * API to get mock Products [ArrayList Implementation].
     *
     * @return Sku's
     * @throws IOException
     */
    @GetMapping(value = {"/products/with/arraylist"})
    List<Sku> getMockProductsFromArrayList() throws IOException {
        return productService.getProductListFromArrayList();
    }

    /**
     * API to get mock Products [LinkedList Implementation].
     *
     * @return Sku's
     * @throws IOException
     */
    @GetMapping(value = {"/products/with/linkedlist"})
    List<Sku> getMockProductsFromLinkedList() throws IOException {
        return productService.getProductListFromLinkedList();
    }

    /**
     * API to get expensive Product from list of available Products.
     *
     * @return Order
     * @throws IOException
     */
    @GetMapping(value = {"/product/expensive"})
    Sku getMockOrder() throws IOException {
        return productService.getExpensiveProduct();
    }

    /**
     * POST API to validate mock Product.
     *
     * @return Order
     * @throws IOException
     */
    @PostMapping(value = {"/product"})
    Optional<Sku> validateOrder(@RequestBody Sku product) {
        return productService.validateProduct(product);
    }
}
