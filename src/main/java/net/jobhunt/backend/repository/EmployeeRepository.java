package net.jobhunt.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.jobhunt.backend.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	
}
