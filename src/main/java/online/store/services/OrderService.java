package online.store.services;

import online.store.model.Order;
import online.store.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final long maxNumberOfItems;

    public OrderService(OrderRepository orderRepository,
                        @Value("${product.service.max-number-of-items:25}") long maxNumberOfItems) {

        this.orderRepository = orderRepository;
        this.maxNumberOfItems = maxNumberOfItems;
    }

    public void placeOrders(Iterable<Order> orders){
        validateNumberOfItemsOrdered(orders);
        orderRepository.saveAll(orders);
    }

    private void validateNumberOfItemsOrdered(Iterable<Order> order){
        long totalNumbersOfItems=0;

        for(Order orders : order){
            totalNumbersOfItems +=orders.getQuantity();

            if(totalNumbersOfItems>maxNumberOfItems){
                throw new IllegalStateException(String.format
                        ("Number of Products %d exceeded the limit of %d",
                                totalNumbersOfItems, maxNumberOfItems));
            }
        }
    }
}
