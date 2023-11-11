package vn.edu.iuh.fit.frontends.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.backends.models.Customer;
import vn.edu.iuh.fit.backends.repositories.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class CustomerController {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/customers")
    public String getAllCustomer(Model model) {
        List<Customer> customers = customerRepository.findAll();
        model.addAttribute("customers", customers);
        return "/admin/customer/listCustomer";
    }

    @GetMapping("/customers/show-add-form")
    public String showAddForm(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customerToAdd", customer);
        return "/admin/customer/add-form";
    }

    @PostMapping("/customers/add")
    public String addCustomer(@ModelAttribute("customerToAdd") Customer customer
            , @RequestParam String name
            , @RequestParam String address
            , @RequestParam String email
            , @RequestParam String phone
    ) {

        customer.setName(name);
        customer.setAddress(address);
        customer.setEmail(email);
        customer.setPhone(phone);
        customerRepository.save(customer);
        return "redirect:/admin/customers";
    }

    @GetMapping("/customers/show-edit-form/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        optionalCustomer.ifPresent(customer -> model.addAttribute("customerToUpdate", customer));
        return "/admin/customer/edit-form";
    }

    @PostMapping("/customers/edit/{id}")
    public String updateCustomer(
            @PathVariable Long id
            , @RequestParam String name
            , @RequestParam String email
            , @RequestParam String address
            , @RequestParam String phone
    ) throws Exception {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer existCustomer = optionalCustomer.get();
            existCustomer.setName(name);
            existCustomer.setEmail(email);
            existCustomer.setAddress(address);
            existCustomer.setPhone(phone);
            customerRepository.save(existCustomer);
        } else {
            throw new Exception("Khong tim thay Customer");
        }
        return "redirect:/admin/customers";
    }

    @GetMapping("/customers/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        optionalCustomer.ifPresent(customerRepository::delete);
        return "redirect:/admin/customers";
    }
}
