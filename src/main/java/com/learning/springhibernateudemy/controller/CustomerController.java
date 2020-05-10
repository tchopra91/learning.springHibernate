package com.learning.springhibernateudemy.controller;

import java.util.List;

import com.learning.springhibernateudemy.entity.Customer;
import com.learning.springhibernateudemy.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String listCustomers(Model model) {
        List<Customer> customers = this.customerService.getCustomers();

        model.addAttribute("customers", customers);

        return "list-customers";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Customer customer = new Customer();

        model.addAttribute("customer", customer);

        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String showFormForAdd(@ModelAttribute("customer") Customer customer) {
        customerService.saveCustomer(customer);

        return "redirect:/customer/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int id, Model model) {
        Customer customer = customerService.getCustomer(id);

        model.addAttribute("customer", customer);

        return "customer-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("customerId") int id) {
        customerService.deleteCustomer(id);

        return "redirect:/customer/list";
    }

    @GetMapping("/search")
    public String search(@RequestParam("searchText") String searchText, Model model) {
        List<Customer> customers;

        if (searchText != null && searchText.trim().length() > 0) {
            customers = customerService.searchCustomers(searchText.trim());
            model.addAttribute("customers", customers);
            return "list-customers";

        } else {
            return "redirect:/customer/list";
        }
    }
}