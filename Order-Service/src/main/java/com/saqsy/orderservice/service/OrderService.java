package com.saqsy.orderservice.service;

import com.saqsy.orderservice.dto.InventoryResponse;
import com.saqsy.orderservice.dto.OrderLineItemsDto;
import com.saqsy.orderservice.dto.OrderRequest;
import com.saqsy.orderservice.model.Order;
import com.saqsy.orderservice.model.OrderLineItems;
import com.saqsy.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    private final RestTemplate restTemplate;

    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto).collect(Collectors.toList());
        order.setOrderLineItemsList(orderLineItems);

        List<String> skuCodes = order.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode).collect(Collectors.toList());

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("http://localhost:8083/api/inventory")
                .queryParam("skuCode",skuCodes);

        // Call inventory service and place order if product in stock
        InventoryResponse[] inventoryResponseArray = restTemplate.getForObject(uriBuilder.toUriString(),InventoryResponse[].class);

        boolean allProductsIsStock = Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse::isInStock);

        if (allProductsIsStock) {
            orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Product is not in stock, please try again later");
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
