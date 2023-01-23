package com.backend.crud.service;

import com.backend.crud.service.dto.EmployeesDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeesService {
  Page<EmployeesDTO> getEmployees(Pageable pageable, Long departmentId);
  EmployeesDTO saveEmployees(EmployeesDTO employeesDTO);
  EmployeesDTO getEmployeeById(Long employeesId);
  void deleteEmployees(Long departmentsId);
}
