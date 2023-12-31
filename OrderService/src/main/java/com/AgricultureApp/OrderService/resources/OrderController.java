package com.AgricultureApp.OrderService.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.AgricultureApp.OrderService.Models.Orders;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/addOrder")
	public Orders addOrder(@RequestBody Orders order) {
		return orderService.addOrder(order);
	}

	@GetMapping("/getAllOrder")
	public List<Orders> getAllOrder() {
		return orderService.getAllOrder();
	}

	@GetMapping("/getOrderByFarmer/{farmerID}")
	public List<Orders> getOrderByFarmer(@PathVariable String farmerID) {
		return orderService.getOrderByFarmer(farmerID);
	}

	@GetMapping("/getOrderByDealer/{dealerID}")
	public List<Orders> getOrderByDealer(@PathVariable String dealerID) {
		return orderService.getOrderByDealer(dealerID);
	}
}
