// OrderControllerTest.java

package com.AgricultureApp.OrderService;

import com.AgricultureApp.OrderService.Models.Orders;
import com.AgricultureApp.OrderService.resources.OrderController;
import com.AgricultureApp.OrderService.resources.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class OrderControllerTest {

    private MockMvc mockMvc;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    public void testAddOrder() throws Exception {
        Orders order = new Orders("1", "dealer1", "farmer1", "crop1", "Dealer 1", "Farmer 1", "Crop 1",
                "1234567890", "9876543210", "Pending");

        when(orderService.addOrder(any(Orders.class))).thenReturn(order);

        mockMvc.perform(MockMvcRequestBuilders.post("/addOrder")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"orderID\": \"1\",\n" +
                        "  \"dealerID\": \"dealer1\",\n" +
                        "  \"farmerID\": \"farmer1\",\n" +
                        "  \"cropID\": \"crop1\",\n" +
                        "  \"dealerName\": \"Dealer 1\",\n" +
                        "  \"farmerName\": \"Farmer 1\",\n" +
                        "  \"cropName\": \"Crop 1\",\n" +
                        "  \"dealerMobile\": \"1234567890\",\n" +
                        "  \"farmerMobile\": \"9876543210\",\n" +
                        "  \"orderStatus\": \"Pending\"\n" +
                        "}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.orderID").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dealerID").value("dealer1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.farmerID").value("farmer1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cropID").value("crop1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dealerName").value("Dealer 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.farmerName").value("Farmer 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cropName").value("Crop 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dealerMobile").value("1234567890"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.farmerMobile").value("9876543210"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.orderStatus").value("Pending"));
    }

    @Test
    public void testGetAllOrder() throws Exception {
        List<Orders> ordersList = new ArrayList<>();
        ordersList.add(new Orders("1", "dealer1", "farmer1", "crop1", "Dealer 1", "Farmer 1", "Crop 1",
                "1234567890", "9876543210", "Pending"));
        ordersList.add(new Orders("2", "dealer2", "farmer2", "crop2", "Dealer 2", "Farmer 2", "Crop 2",
                "1111111111", "2222222222", "Approved"));

        when(orderService.getAllOrder()).thenReturn(ordersList);

        mockMvc.perform(MockMvcRequestBuilders.get("/getAllOrder"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].orderID").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dealerID").value("dealer1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].farmerID").value("farmer1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].cropID").value("crop1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dealerName").value("Dealer 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].farmerName").value("Farmer 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].cropName").value("Crop 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dealerMobile").value("1234567890"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].farmerMobile").value("9876543210"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].orderStatus").value("Pending"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].orderID").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].dealerID").value("dealer2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].farmerID").value("farmer2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].cropID").value("crop2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].dealerName").value("Dealer 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].farmerName").value("Farmer 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].cropName").value("Crop 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].dealerMobile").value("1111111111"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].farmerMobile").value("2222222222"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].orderStatus").value("Approved"));
    }

    @Test
    public void testGetOrderByFarmer() throws Exception {
        List<Orders> ordersList = new ArrayList<>();
        ordersList.add(new Orders("1", "dealer1", "farmer1", "crop1", "Dealer 1", "Farmer 1", "Crop 1",
                "1234567890", "9876543210", "Pending"));
        ordersList.add(new Orders("3", "dealer3", "farmer1", "crop3", "Dealer 3", "Farmer 1", "Crop 3",
                "3333333333", "9876543210", "Pending"));

        when(orderService.getOrderByFarmer("farmer1")).thenReturn(ordersList);

        mockMvc.perform(MockMvcRequestBuilders.get("/getOrderByFarmer/farmer1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].orderID").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dealerID").value("dealer1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].farmerID").value("farmer1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].cropID").value("crop1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dealerName").value("Dealer 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].farmerName").value("Farmer 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].cropName").value("Crop 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dealerMobile").value("1234567890"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].farmerMobile").value("9876543210"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].orderStatus").value("Pending"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].orderID").value("3"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].dealerID").value("dealer3"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].farmerID").value("farmer1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].cropID").value("crop3"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].dealerName").value("Dealer 3"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].farmerName").value("Farmer 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].cropName").value("Crop 3"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].dealerMobile").value("3333333333"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].farmerMobile").value("9876543210"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].orderStatus").value("Pending"));
    }

    @Test
    public void testGetOrderByDealer() throws Exception {
        List<Orders> ordersList = new ArrayList<>();
        ordersList.add(new Orders("2", "dealer2", "farmer2", "crop2", "Dealer 2", "Farmer 2", "Crop 2",
                "1111111111", "2222222222", "Approved"));

        when(orderService.getOrderByDealer("dealer2")).thenReturn(ordersList);

        mockMvc.perform(MockMvcRequestBuilders.get("/getOrderByDealer/dealer2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].orderID").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dealerID").value("dealer2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].farmerID").value("farmer2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].cropID").value("crop2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dealerName").value("Dealer 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].farmerName").value("Farmer 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].cropName").value("Crop 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dealerMobile").value("1111111111"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].farmerMobile").value("2222222222"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].orderStatus").value("Approved"));
    }
}
