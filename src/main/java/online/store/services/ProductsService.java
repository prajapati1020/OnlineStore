package online.store.services;

import online.store.model.Product;
import online.store.model.ProductCategory;
import online.store.repositories.ProductCategoryRepository;
import online.store.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class ProductsService {
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepositorery;

    public ProductsService(ProductRepository productRepository, ProductCategoryRepository productCategoryRepositorery) {
        this.productRepository = productRepository;
        this.productCategoryRepositorery = productCategoryRepositorery;
    }

    public List<String> getAllSupportedCategories() {
        return productCategoryRepositorery.findAll()
                .stream()
                .map(
                productCategory -> productCategory.getCategory())
                .collect(Collectors.toList());
    }


    public List<Product> getDealsOfTheDay(int atMostNumbersOfProducts){
        return productRepository.findAtMostNumberOfProducts(atMostNumbersOfProducts);
    }

    public List<Product> getProductByCategory(String productCategory){
        return productRepository.findByCategory(productCategory);
    }
}
