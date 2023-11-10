package vn.edu.iuh.fit.frontends.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.backends.enums.EmployeeStatus;
import vn.edu.iuh.fit.backends.models.Employee;
import vn.edu.iuh.fit.backends.repositories.EmployeeRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/admin")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/employees")
    public String showAllEmployee(Model model) {
        List<Employee> employees = employeeRepository.findAll();
        model.addAttribute("listEmployee", employees);
        return "/admin/employee/listEmp";
    }

    @GetMapping("/employee/show-add-form")
    public String showAddForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employeeToAdd", employee);
        return "/admin/employee/add-form";
    }

    @PostMapping("/employees/add")
    public String addEmployee(@RequestParam String fullName,
                              @RequestParam String address,
                              @RequestParam String email,
                              @RequestParam String dob,
                              @RequestParam String phone) {
        Employee employee = new Employee();
        employee.setFullname(fullName);
        employee.setAddress(address);
        employee.setEmail(email);
        employee.setDob(LocalDate.parse(dob));
        employee.setPhone(phone);
        employee.setStatus(EmployeeStatus.ACTIVE);

        employeeRepository.save(employee);

        return "redirect:/admin/employees";
    }

    @GetMapping("/employees/show-edit-form/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            model.addAttribute("employeeToUpdate", optionalEmployee.get());
            return "/admin/employee/edit-form";
        }
        return "/admin/employee/listEmp";
    }

    @PostMapping("/employees/edit/{id}")
    public String updateEmployee(@PathVariable Long id,
                                 @RequestParam String fullName,
                                 @RequestParam String address,
                                 @RequestParam String email,
                                 @RequestParam String dob,
                                 @RequestParam String phone) throws Exception {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee existingEmployee = optionalEmployee.get();
            // Cập nhật thông tin của nhân viên
            existingEmployee.setFullname(fullName);
            existingEmployee.setEmail(email);
            existingEmployee.setAddress(address);
            existingEmployee.setDob(LocalDate.parse(dob));
            existingEmployee.setPhone(phone);

            employeeRepository.save(existingEmployee);
        } else {
            throw new Exception("Khong tim thay Employee!");
        }
        return "redirect:/admin/employees";
    }

    @GetMapping("/employees/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        optionalEmployee.ifPresent(employeeRepository::delete);
        return "redirect:/admin/employees";
    }
}
