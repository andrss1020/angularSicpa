package com.backend.crud.service.repository.custom;

import com.backend.crud.service.dto.EmployeesDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CustomEmployeesRepository {
    Page<EmployeesDTO> loadPages(Pageable pageable, Long employeesId);

}
