package com.carrentingsystem.controller;

import com.carrentingsystem.entity.Account;
import com.carrentingsystem.entity.Customer;
import com.carrentingsystem.service.AccountService;
import com.carrentingsystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class AuthController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String customerName,
                           @RequestParam String email,
                           @RequestParam String password,
                           @RequestParam String mobile,
                           @RequestParam String birthday,
                           @RequestParam String identityCard,
                           @RequestParam String licenceNumber,
                           @RequestParam String licenceDate,
                           Model model) {
        if (customerService.findByEmail(email) != null) {
            model.addAttribute("error", "Email already exists!");
            return "register";
        }

        Account customerAccount = new Account();
        customerAccount.setAccountName(email);
        customerAccount.setRole("CUSTOMER");
        Account savedAccount = accountService.saveAccount(customerAccount);

        Customer customer = new Customer();
        customer.setCustomerName(customerName);
        customer.setEmail(email);
        customer.setPassword(passwordEncoder.encode(password));
        customer.setMobile(mobile);
        customer.setBirthday(LocalDate.parse(birthday));
        customer.setIdentityCard(identityCard);
        customer.setLicenceNumber(licenceNumber);
        customer.setLicenceDate(LocalDate.parse(licenceDate));
        customer.setAccount(savedAccount);

        customerService.saveCustomer(customer);

        return "redirect:/login?registered";
    }
}
