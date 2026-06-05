package online.store.controllers;


import online.store.services.ProductsService;
import org.springframework.web.bind.annotation.GetMapping;
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
}
