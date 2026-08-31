package com.swiggy.ecomm.service;

import com.swiggy.ecomm.dto.OrderItemRequest;
import com.swiggy.ecomm.exception.ResourceNotFoundException;
import com.swiggy.ecomm.model.Order;
import com.swiggy.ecomm.model.OrderItem;
import com.swiggy.ecomm.model.Product;
import com.swiggy.ecomm.repository.OrderItemRepository;
import com.swiggy.ecomm.repository.OrderRepository;
import com.swiggy.ecomm.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderItemService(OrderItemRepository orderItemRepository, OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public OrderItem create(OrderItemRequest request) {
        OrderItem item = new OrderItem();
        applyRequest(item, request);
        return orderItemRepository.save(item);
    }

    public OrderItem getById(Long id) {
        return orderItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order item not found with id " + id));
    }

    public List<OrderItem> getAll() {
        return orderItemRepository.findAll();
    }

    public OrderItem update(Long id, OrderItemRequest request) {
        OrderItem item = getById(id);
        applyRequest(item, request);
        return orderItemRepository.save(item);
    }

    public void delete(Long id) {
        OrderItem item = getById(id);
        orderItemRepository.delete(item);
    }

    private void applyRequest(OrderItem item, OrderItemRequest request) {
        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + request.getOrderId()));
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + request.getProductId()));
        item.setOrder(order);
        item.setProduct(product);
        item.setQuantity(request.getQuantity());
    }
}
