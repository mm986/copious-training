package com.copious.training.designpattern.factory;

import com.copious.training.api.errors.InvalidProductException;
import com.copious.training.constants.ExceptionCodeEnum;
import com.copious.training.constants.ProductCategoryEnum;
import com.copious.training.designpattern.factory.varients.HomeAndGardenProduct;
import com.copious.training.designpattern.factory.varients.HomeImprovementProduct;
import com.copious.training.designpattern.factory.varients.MedicalHealthProduct;
import com.copious.training.designpattern.factory.varients.SportingGoodsProduct;
import com.copious.training.designpattern.factory.varients.StyleAndFashionProduct;
import com.copious.training.designpattern.factory.varients.HealthAndWellnessProduct;
import com.copious.training.designpattern.factory.varients.ChildrenAndInfantsProduct;
import com.copious.training.designpattern.factory.varients.PetsAndPetSuppliersProduct;
import com.copious.training.designpattern.factory.varients.ApparelAndAccessoriesProduct;
import com.copious.training.designpattern.factory.varients.ConsumerElectronicGoodsProduct;
import com.copious.training.designpattern.factory.varients.Product;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author Mahesh More
 * <p>
 * Product Factory to get Products by Category.
 */
@Service
public class ProductFactory {
    /**
     * Factory method to get products by category.
     *
     * @param category
     * @return Product
     * @throws IOException
     */
    public Product getProductFactory(ProductCategoryEnum category) throws IOException {
        switch (category) {
            case MEDICAL_HEALTH:
                return new MedicalHealthProduct();
            case SPORTING_GOODS:
                return new SportingGoodsProduct();
            case HOME_AND_GARDEN:
                return new HomeAndGardenProduct();
            case HOME_IMPROVEMENT:
                return new HomeImprovementProduct();
            case STYLE_AND_FASHION:
                return new StyleAndFashionProduct();
            case HEALTH_AND_WELLNESS:
                return new HealthAndWellnessProduct();
            case CHILDREN_AND_INFANTS:
                return new ChildrenAndInfantsProduct();
            case PETS_AND_PET_SUPPLIES:
                return new PetsAndPetSuppliersProduct();
            case APPAREL_AND_ACCESSORIES:
                return new ApparelAndAccessoriesProduct();
            case CONSUMER_ELECTRONIC_GOODS:
                return new ConsumerElectronicGoodsProduct();
            default:
                throw new InvalidProductException(ExceptionCodeEnum.BAD_REQUEST,
                        ExceptionCodeEnum.BAD_REQUEST.getMessage(),
                        "Invalid Product Category Product Category should be one of the following: "
                                + ProductCategoryEnum.values()
                );
        }
    }
}