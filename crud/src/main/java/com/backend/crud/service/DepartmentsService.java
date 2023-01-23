package com.backend.crud.service;

import com.backend.crud.service.dto.DepartmentsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DepartmentsService {
  Page<DepartmentsDTO> getDepartments(Pageable pageable, Long enterpriseId);
  DepartmentsDTO saveDepartments(DepartmentsDTO departmentsDTO);
  DepartmentsDTO getDepartmentById(Long departmentsId);
  void deleteDepartments(Long departmentsId);
}
