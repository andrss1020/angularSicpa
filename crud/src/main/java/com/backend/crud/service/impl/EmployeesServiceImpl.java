package com.backend.crud.service.impl;

import com.backend.crud.domain.Employees;
import com.backend.crud.service.EmployeesService;
import com.backend.crud.service.dto.EmployeesDTO;
import com.backend.crud.service.enumeration.Status;
import com.backend.crud.service.mapper.EmployeesMapper;
import com.backend.crud.service.repository.EmployeesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeesServiceImpl implements EmployeesService {

    private final Logger log = LoggerFactory.getLogger(EmployeesServiceImpl.class);
    private final EmployeesRepository employyesRepository;
    private EmployeesMapper employeesMapper;

    public EmployeesServiceImpl(EmployeesMapper employeesMapper, EmployeesRepository employyesRepository) {
        this.employeesMapper = employeesMapper;
        this.employyesRepository = employyesRepository;
    }


    @Override
    public Page<EmployeesDTO> getEmployees(Pageable pageable, Long departmentsId) {
        log.debug("Request to get BagBox");
        return employyesRepository.loadPages(pageable, departmentsId);
    }

    @Override
    public EmployeesDTO saveEmployees(EmployeesDTO employeesDTO) {
        log.debug("Request to save a employeesDTO : {}", employeesDTO);

        if (employeesDTO.getId() == null) {
            employeesDTO.setStatus(Status.ACTIVE);
        }

        Employees enterprises = employeesMapper.toEntity(employeesDTO);
        employyesRepository.save(enterprises);
        return null;
    }

    @Override
    public EmployeesDTO getEmployeeById(Long employeesId) {
        return employyesRepository.getEmployeesById(employeesId);
    }

    @Override
    public void deleteEmployees(Long departmentsId) {

    }
}
