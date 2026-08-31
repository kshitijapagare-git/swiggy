package com.swiggy.ecomm.service;

import com.swiggy.ecomm.dto.OrderRequest;
import com.swiggy.ecomm.exception.ResourceNotFoundException;
import com.swiggy.ecomm.model.Order;
import com.swiggy.ecomm.model.OrderItem;
import com.swiggy.ecomm.model.Product;
import com.swiggy.ecomm.repository.OrderRepository;
import com.swiggy.ecomm.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public Order create(OrderRequest request) {
        Order order = new Order();
        applyRequest(order, request);
        return orderRepository.save(order);
    }

    public Order getById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order update(Long id, OrderRequest request) {
        Order order = getById(id);
        applyRequest(order, request);
        return orderRepository.save(order);
    }

    public void delete(Long id) {
        Order order = getById(id);
        orderRepository.delete(order);
    }

    private void applyRequest(Order order, OrderRequest request) {
        order.setCustomerName(request.getCustomerName());
        order.setStatus(request.getStatus());

        order.getItems().clear();
        List<OrderItem> items = new ArrayList<>();
        request.getItems().forEach(itemRequest -> {
            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + itemRequest.getProductId()));
            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(itemRequest.getQuantity());
            item.setUnitPrice(itemRequest.getUnitPrice());
            items.add(item);
        });
        order.getItems().addAll(items);
    }
}
