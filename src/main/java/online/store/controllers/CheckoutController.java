package online.store.controllers;

import online.store.exceptions.CreditCardValidationException;
import online.store.model.CheckoutRequest;
import online.store.model.Order;
import online.store.repositories.ProductRepository;
import online.store.services.OrderService;
import online.store.services.ProductsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
public class CheckoutController {
    private final OrderService orderService;
    private final ProductsService productsService;
    private final CreditCardValidationException creditCardValidationException;

    public CheckoutController(OrderService orderService, ProductsService productsService, CreditCardValidationException creditCardValidationException) {
        this.orderService = orderService;
        this.productsService = productsService;
        this.creditCardValidationException = creditCardValidationException;
    }

    @PostMapping("/checkout")
    public ResponseEntity<String> checkout(CheckoutRequest checkoutRequest){
        Set<Order> orders = new HashSet<>(checkoutRequest.getProducts().size());

        if(isNullOrBlank(checkoutRequest.getCreditCard())){
            return new ResponseEntity<>("Credit card information is missing", HttpStatus.PAYMENT_REQUIRED);
        }

        if(isNullOrBlank(checkoutRequest.getFirstName())){
            return new ResponseEntity<>("First name is missing",HttpStatus.BAD_REQUEST);
        }

        if(isNullOrBlank(checkoutRequest.getLastName())){
            return new ResponseEntity<>("Last name is missing", HttpStatus.BAD_REQUEST);
        }

        for(CheckoutRequest.ProductInfo productInfo : checkoutRequest.getProducts()){
             Order order = new Order(checkoutRequest.getFirstName(),
                     checkoutRequest.getLastName(),checkoutRequest.getEmail(),
                     checkoutRequest.getShippingAddress(),
                     productInfo.getQuantity(),productsService.getProductById(productInfo.getProductInfo()),checkoutRequest.getCreditCard());

             orders.add(order);
        }

        orderService.placeOrders(orders);

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    private static boolean isNullOrBlank(String input){
        return input==null || input.isEmpty()||input.trim().length()==0;
    }

}
