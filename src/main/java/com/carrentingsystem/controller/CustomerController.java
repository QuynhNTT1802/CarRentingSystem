package com.carrentingsystem.controller;

import com.carrentingsystem.entity.*;
import com.carrentingsystem.service.*;
import com.carrentingsystem.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired private CustomerService customerService;
    @Autowired private CarService carService;
    @Autowired private CarRentalService carRentalService;

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        model.addAttribute("customer", userDetails.getCustomer());
        return "customer/profile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@AuthenticationPrincipal CustomUserDetails userDetails,
                                @RequestParam String customerName,
                                @RequestParam String mobile) {
        Customer customer = userDetails.getCustomer();
        customer.setCustomerName(customerName);
        customer.setMobile(mobile);
        customerService.saveCustomer(customer);
        return "redirect:/customer/profile?updated";
    }

    @GetMapping("/rent")
    public String rentForm(Model model) {
        model.addAttribute("cars", carService.getCarsByStatus("Active"));
        return "customer/rent";
    }

    @PostMapping("/rent")
    public String createRental(@AuthenticationPrincipal CustomUserDetails userDetails,
                               @RequestParam List<Long> carIds,
                               @RequestParam String pickupDate,
                               @RequestParam String returnDate,
                               @RequestParam Double rentPrice) {
        Customer customer = userDetails.getCustomer();
        LocalDate pickup = LocalDate.parse(pickupDate);
        LocalDate ret = LocalDate.parse(returnDate);

        for (Long carId : carIds) {
            Car car = carService.getCarById(carId);
            if (car != null) {
                CarRental rental = new CarRental();
                rental.setCustomer(customer);
                rental.setCar(car);
                rental.setPickupDate(pickup);
                rental.setReturnDate(ret);
                rental.setRentPrice(rentPrice);
                rental.setStatus("Active");
                carRentalService.saveRental(rental);
            }
        }
        return "redirect:/customer/history?success";
    }

    @GetMapping("/history")
    public String history(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        Customer customer = userDetails.getCustomer();
        model.addAttribute("rentals", carRentalService.getRentalsByCustomer(customer));
        return "customer/history";
    }
}
