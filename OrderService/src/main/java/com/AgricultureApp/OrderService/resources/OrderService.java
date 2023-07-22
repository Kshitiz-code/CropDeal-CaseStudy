package com.AgricultureApp.OrderService.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AgricultureApp.OrderService.Models.Orders;



@Service
public class OrderService {
	
	

	@Autowired
	private OrderRepository orderRepository;

	public Orders addOrder(Orders order) {
		return orderRepository.save(order);
	}

	public List<Orders> getAllOrder() {
		return orderRepository.findAll();
	}

	public List<Orders> getOrderByFarmer(String farmerID) {
		return orderRepository.findByfarmerID(farmerID);
	}

	public List<Orders> getOrderByDealer(String dealerID) {
		return orderRepository.findBydealerID(dealerID);
	}
}
