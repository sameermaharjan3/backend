package net.jobhunt.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.jobhunt.backend.exception.ResourceNotFoundException;
import net.jobhunt.backend.model.Employee;
import net.jobhunt.backend.repository.EmployeeRepository;

@CrossOrigin("*")
@RestController
@RequestMapping
public class EmployeeController {

		@Autowired
		private EmployeeRepository employeeRepository;
		
		@GetMapping("/")
		public String defaultHomepage(){
			return "Welcome to EMS!";
		}
		@GetMapping("/employees")
		public List<Employee> getAllEmployees(){
			return employeeRepository.findAll();
		}
		
		@PostMapping
		public Employee createEmployee(@RequestBody Employee employee) {
			return employeeRepository.save(employee);
		}
		
		@GetMapping("/employees/{id}")
		public ResponseEntity<Employee> getEmployeeById(@PathVariable long id){
			Employee employee = employeeRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Employee doesn't exist with id :" + id));
			return ResponseEntity.ok(employee);
		}
		
		@PutMapping("/employees/{id}")
		public ResponseEntity<Employee> updateEmployee(@PathVariable long id, @RequestBody Employee employee){
			Employee updatedEmployee = employeeRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Employee Not Found with id :" + id));
			updatedEmployee.setFirstName(employee.getFirstName());
			updatedEmployee.setLastName(employee.getLastName());
			updatedEmployee.setEmailId(employee.getEmailId());

			employeeRepository.save(updatedEmployee);
			
			return ResponseEntity.ok(updatedEmployee);
		}
		
		@DeleteMapping("/employees/{id}")
		public String deleteEmployee(@PathVariable long id){
			Employee retrieveEmployee = employeeRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Employee Not Found with id :" + id));
			employeeRepository.delete(retrieveEmployee);
			
			return "Deleted";
		}
}
