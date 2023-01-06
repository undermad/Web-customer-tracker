package com.example.webcustomertracker.controller;

import com.example.webcustomertracker.SortUtils;
import com.example.webcustomertracker.dao.CustomerDAO;
import com.example.webcustomertracker.pojo.Customer;
import com.example.webcustomertracker.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String listCustomers(@RequestParam(required = false) String sort,
                                Model model) {
        if (sort == null) {
            sort = String.valueOf(SortUtils.FIRST_NAME);
        }

        model.addAttribute("customers", customerService.getCustomers(Integer.valueOf(sort)));
        return "list-customer";
    }

    @GetMapping("/showAddCustomerForm")
    public String addCustomer(Model model) {

        Customer customer = new Customer();
        model.addAttribute("customer", customer);

        return "add-customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer")
                               Customer customer) {

        customerService.saveCustomer(customer);

        return "redirect:/customer/list";
    }

    @GetMapping("/showUpdateForm")
    public String showUpdateForm(@RequestParam("customerId") int id,
                                 Model model) {

        model.addAttribute("customer", customerService.getCustomer(id));

        return "add-customer-form";
    }

    @GetMapping("/deleteCustomer")
    public String deleteCustomer(@RequestParam("customerId") int id) {

        customerService.deleteCustomer(id);

        return "redirect:/customer/list";
    }

    @GetMapping("/search")
    public String searchCustomer(@RequestParam("customerName") String customerName,
                                 Model model) {

        List<Customer> customerList = customerService.searchCustomer(customerName);
        model.addAttribute("customers", customerList);

        return "list-customer";
    }
}
