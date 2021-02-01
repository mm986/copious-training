/*//package com.copious.training.data;
//
//import com.copious.training.dao.ProductRepository;
//import com.copious.training.domain.Sku;
//import com.copious.training.entity.SkuEntity;
//import com.copious.training.repository.SkuEntityRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Component
//public class ProductLoader implements ApplicationRunner {

//    @Autowired
//    SkuEntityRepository productRepository;
//
//    @Autowired
//    ProductRepository mockProducts;

//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        productRepository.saveAll(mapSkuDomainToSkuEntity(mockProducts.getMockProducts()));
//    }
//
//    private List<SkuEntity> mapSkuDomainToSkuEntity(List<Sku> skus) {
//        return skus
//                .stream()
//                .map(sku -> {
//                    SkuEntity skuEntity = new SkuEntity();
//                    return skuEntity;
//                }).collect(Collectors.toList());
//    }
//}*/
