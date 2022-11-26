package com.example.orderservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.orderservice.dto.InventoryReponse;
import com.example.orderservice.dto.OrderLineItemsDto;
import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.models.Order;
import com.example.orderservice.models.OrderLineItems;
import com.example.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient webClient;
    
    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();

        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtos().stream()
            .map(this::mapToDto)
            .toList();
        
        order.setOrderLineItemsList(orderLineItems);

        List<String> skuCodes = order.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode).toList();

        // Call Inventory Service, and place order if in stock
        InventoryReponse[] inventoryReponses = webClient.get()
            .uri("http://localhost:8082/api/inventory/", 
                uriBuilder -> uriBuilder.queryParam("skucode", skuCodes).build()
            )
            .retrieve()
            .bodyToMono(InventoryReponse[].class)
            .block();

        boolean allProductsInStock = Arrays.stream(inventoryReponses).allMatch(InventoryReponse::isInStock);
        
        if(!allProductsInStock) {
            throw new IllegalArgumentException("Product not in stock, please try again later");
        }else {
            orderRepository.save(order);
        }

    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());

        return orderLineItems;
    }

}
