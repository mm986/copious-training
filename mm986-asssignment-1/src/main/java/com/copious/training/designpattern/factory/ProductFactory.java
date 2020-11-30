package com.copious.training.designpattern.factory;

import com.copious.training.api.errors.InvalidProductException;
import com.copious.training.constants.ProductCategory;
import com.copious.training.designpattern.factory.varients.*;
import com.copious.training.domain.Sku;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author Mahesh More
 * <p>
 * Product Factory to get Products by Category.
 */
@Service
public class ProductFactory {
    /**
     * Factory method to get products by category.
     * @param category
     * @return
     * @throws IOException
     */
    public List<Sku> getProducts(ProductCategory category, List<Sku> products) throws IOException {
        switch (category) {
            case MEDICAL_HEALTH:
                return new MedicalHealthProduct().getProducts(products);
            case SPORTING_GOODS:
                return new SportingGoodsProduct().getProducts(products);
            case HOME_AND_GARDEN:
                return new HomeAndGardenProduct().getProducts(products);
            case HOME_IMPROVEMENT:
                return new HomeImprovementProduct().getProducts(products);
            case STYLE_AND_FASHION:
                return new StyleAndFashionProduct().getProducts(products);
            case HEALTH_AND_WELLNESS:
                return new HealthAndWellnessProduct().getProducts(products);
            case CHILDREN_AND_INFANTS:
                return new ChildrenAndInfantsProduct().getProducts(products);
            case PETS_AND_PET_SUPPLIES:
                return new PetsAndPetSuppliersProduct().getProducts(products);
            case APPAREL_AND_ACCESSORIES:
                return new ApparelAndAccessoriesProduct().getProducts(products);
            case CONSUMER_ELECTRONIC_GOODS:
                return new ConsumerElectronicGoodsProduct().getProducts(products);
            default:
                throw new InvalidProductException(HttpStatus.BAD_REQUEST, "Invalid Product Category. "
                        + "Product Category should be one of the following: "
                        + ProductCategory.values()
                );
        }
    }
}