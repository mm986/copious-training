package com.copious.training.api;

import com.copious.training.domain.Sku;
import com.copious.training.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

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

}
