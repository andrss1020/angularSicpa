package com.backend.crud.service.repository.custom;

import com.backend.crud.domain.Departments;
import com.backend.crud.service.dto.DepartmentsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CustomDepartmentsRepository {
    Page<DepartmentsDTO> loadPages(Pageable pageable, Long enterpriseId);

    Optional<Departments> findOne(Long id);
}
