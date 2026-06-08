package online.store.controllers;


import online.store.model.wrappers.ProductWrapper;
import online.store.services.ProductsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
public class HomePageController {

    private final ProductsService productsService;

    public HomePageController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/Categories")
    public String getProductCategories(){
        return productsService.getAllSupportedCategories()
                .stream().collect(Collectors.joining(","));
    }

    @GetMapping("/deal_of_the_day/{number_of_products}")
    public ProductWrapper getDealOfTheDay(@PathVariable(name="number_of_products")int numberOfProducts){
        return new ProductWrapper(productsService.getDealsOfTheDay(numberOfProducts));
    }

    @GetMapping("/products")
    public ProductWrapper getProductsByCategory(@RequestParam(name = "category")String category){
        return new ProductWrapper(productsService.getProductByCategory(category));
    }

    @GetMapping("/getAllProducts")
    public ProductWrapper getAllProducts(){
        return new ProductWrapper(productsService.getAllProducts());
    }

}
