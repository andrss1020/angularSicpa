package com.backend.crud.service.impl;

import com.backend.crud.domain.Departments;
import com.backend.crud.domain.Enterprises;
import com.backend.crud.service.DepartmentsService;
import com.backend.crud.service.dto.DepartmentsDTO;
import com.backend.crud.service.enumeration.Status;
import com.backend.crud.service.mapper.DepartmentsMapper;
import com.backend.crud.service.repository.DepartmentsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DepartmentsServiceImpl implements DepartmentsService {

    private final Logger log = LoggerFactory.getLogger(DepartmentsServiceImpl.class);
    private final DepartmentsRepository departmentsRepository;
    private DepartmentsMapper departmentsMapper;

    public DepartmentsServiceImpl(DepartmentsMapper departmentsMapper, DepartmentsRepository departmentsRepository) {
        this.departmentsMapper = departmentsMapper;
        this.departmentsRepository = departmentsRepository;
    }


    @Override
    public Page<DepartmentsDTO> getDepartments(Pageable pageable, Long enterpriseId) {
        log.debug("Request to get BagBox");
        return departmentsRepository.loadPages(pageable, enterpriseId);
    }

    @Override
    public DepartmentsDTO saveDepartments(DepartmentsDTO departmentsDTO) {
        log.debug("Request to save a departmentsDTODTO : {}", departmentsDTO);

        if (departmentsDTO.getId() == null) {
            departmentsDTO.setStatus(Status.ACTIVE);
        }

        Departments enterprises = departmentsMapper.toEntity(departmentsDTO);
        departmentsRepository.save(enterprises);
        return null;
    }

    @Override
    public DepartmentsDTO getDepartmentById(Long departmentsId) {
        return departmentsRepository.getDepartmentById(departmentsId);
    }

    @Override
    public void deleteDepartments(Long departmentsId) {

    }
}
