package com.carrentingsystem.controller;

import com.carrentingsystem.entity.*;
import com.carrentingsystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired private CustomerService customerService;
    @Autowired private CarService carService;
    @Autowired private CarProducerService carProducerService;
    @Autowired private CarRentalService carRentalService;
    @Autowired private AccountService accountService;
    @Autowired private PasswordEncoder passwordEncoder;

    @GetMapping("/dashboard")
    public String dashboard() {
        return "admin/dashboard";
    }

    @GetMapping("/customers")
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "admin/customers";
    }

    @GetMapping("/customers/add")
    public String addCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "admin/customer-form";
    }

    @PostMapping("/customers/save")
    public String saveCustomer(@ModelAttribute Customer customer,
                               @RequestParam String role,
                               @RequestParam(required = false) String password) {
        if (customer.getCustomerID() == null) {
            Account acc = new Account();
            acc.setAccountName(customer.getEmail());
            acc.setRole(role);
            accountService.saveAccount(acc);
            customer.setAccount(acc);
            customer.setPassword(passwordEncoder.encode(password));
        } else {
            Customer existing = customerService.getCustomerById(customer.getCustomerID());
            customer.setAccount(existing.getAccount());
            customer.setPassword(password != null && !password.isEmpty()
                    ? passwordEncoder.encode(password)
                    : existing.getPassword());
            existing.getAccount().setRole(role);
            accountService.saveAccount(existing.getAccount());
        }
        customerService.saveCustomer(customer);
        return "redirect:/admin/customers";
    }

    @GetMapping("/customers/edit/{id}")
    public String editCustomerForm(@PathVariable Long id, Model model) {
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer);
        model.addAttribute("currentRole", customer.getAccount().getRole());
        return "admin/customer-form";
    }

    @GetMapping("/customers/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return "redirect:/admin/customers";
    }

    @GetMapping("/cars")
    public String listCars(Model model) {
        model.addAttribute("cars", carService.getAllCars());
        return "admin/cars";
    }

    @GetMapping("/cars/add")
    public String addCarForm(Model model) {
        model.addAttribute("car", new Car());
        model.addAttribute("producers", carProducerService.getAllProducers());
        return "admin/car-form";
    }

    @PostMapping("/cars/save")
    public String saveCar(@ModelAttribute Car car, @RequestParam Long producerId) {
        car.setProducer(carProducerService.getProducerById(producerId));
        carService.saveCar(car);
        return "redirect:/admin/cars";
    }

    @GetMapping("/cars/edit/{id}")
    public String editCarForm(@PathVariable Long id, Model model) {
        model.addAttribute("car", carService.getCarById(id));
        model.addAttribute("producers", carProducerService.getAllProducers());
        return "admin/car-form";
    }

    @GetMapping("/cars/delete/{id}")
    public String deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return "redirect:/admin/cars";
    }

    @GetMapping("/rentals")
    public String listRentals(Model model) {
        model.addAttribute("rentals", carRentalService.getAllRentals());
        return "admin/rentals";
    }

    @PostMapping("/rentals/delete")
    public String deleteRental(@RequestParam Long customerId, @RequestParam Long carId) {
        CarRental rental = carRentalService.getRentalByCustomerIdAndCarId(customerId, carId);
        if (rental != null) {
            carRentalService.deleteRental(rental);
        }
        return "redirect:/admin/rentals";
    }

    @GetMapping("/report")
    public String reportForm() {
        return "admin/report";
    }

    @PostMapping("/report")
    public String generateReport(@RequestParam String startDate,
                                 @RequestParam String endDate,
                                 Model model) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        model.addAttribute("rentals", carRentalService.getRentalReport(start, end));
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        return "admin/report";
    }
}
