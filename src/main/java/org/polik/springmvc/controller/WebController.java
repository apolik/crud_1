package org.polik.springmvc.controller;

import org.polik.springmvc.entity.Employee;
import org.polik.springmvc.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WebController {
    private EmployeeService service;

    @Autowired
    public WebController(EmployeeService service) {
        this.service = service;
    }

    @RequestMapping("/")
    public String showAllEmployees(Model model) {
        List<Employee> result = service.getAllEmployees();
        model.addAttribute("allEmps", result);

        return "all-employees";
    }

    @RequestMapping("/create")
    public String createEmployee(Model model) {
        model.addAttribute("employee", new Employee());

        return "add-employee";
    }

    @RequestMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        service.saveEmployee(employee);

        return "redirect:/";
    }

    @RequestMapping("/edit")
    public String editEmployee(@RequestParam("tempId") long id, Model model) {
        model.addAttribute("employee", service.getEmployeeById(id));

        return "add-employee";
    }

    @RequestMapping("/delete")
    public String deleteEmployee(@RequestParam("tempId") long id) {
        System.out.println(id);
        service.deleteEmployee(id);

        return "redirect:/";
    }

    @RequestMapping("/test")
    public Employee test() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("name");
        employee.setDepartment("IT");
        employee.setSurname("surname");
        employee.setSalary(50000);

        return employee;
    }

//    @RequestMapping("/editEmployee")
//    public String editEmployeeById(@ModelAttribute("employee") Employee employee, Model model) {
//        System.out.println(employee);
//        System.out.println(model.asMap().toString());
//
//        return "redirect:/";
//    }
}
