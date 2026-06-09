package online.store.controllers;

import online.store.model.CheckoutRequest;
import online.store.model.Order;
import online.store.repositories.ProductRepository;
import online.store.services.OrderService;
import online.store.services.ProductsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
public class CheckoutController {
    private final OrderService orderService;
    private final ProductsService productsService;

    public CheckoutController(OrderService orderService, ProductsService productsService) {
        this.orderService = orderService;
        this.productsService = productsService;
    }

    @PostMapping("/checkout")
    public String checkout(CheckoutRequest checkoutRequest){
        Set<Order> orders = new HashSet<>(checkoutRequest.getProducts().size());

        for(CheckoutRequest.ProductInfo productInfo : checkoutRequest.getProducts()){
             Order order = new Order(checkoutRequest.getFirstName(),
                     checkoutRequest.getLastName(),checkoutRequest.getEmail(),
                     checkoutRequest.getShippingAddress(),
                     productInfo.getQuantity(),productsService.getProductById(productInfo.getProductInfo()),checkoutRequest.getCreditCard());

             orders.add(order);
        }

        orderService.placeOrders(orders);

        return "success";
    }

}
