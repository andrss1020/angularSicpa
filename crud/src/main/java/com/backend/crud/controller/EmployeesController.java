package com.backend.crud.controller;

import com.backend.crud.service.EmployeesService;
import com.backend.crud.service.dto.EmployeesDTO;
import com.backend.crud.util.PaginationUtil;
import com.backend.crud.util.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api",
        produces = "application/json")
public class EmployeesController {
    private final Logger log = LoggerFactory.getLogger(EmployeesController.class);

    private static final String ENTITY_NAME = "employees";

    @Autowired
    private final EmployeesService employeesService;
    public EmployeesController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    @GetMapping("/employees/load-page/{departmentsId}")
    public ResponseEntity<List<EmployeesDTO>> getEmployeesLoadPage(@PathVariable Long departmentsId, Pageable pageable) {
      log.debug("REST request to get employees");
      Page<EmployeesDTO> page = employeesService.getEmployees(pageable, departmentsId);
      HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
      return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeesDTO> getEmployeesById(@PathVariable(value = "id", required = true) Long employeesId) throws URISyntaxException {
        log.debug("REST request to get employees by id : {}", employeesId);
        EmployeesDTO result = employeesService.getEmployeeById(employeesId);
        if (result == null) {
            throw new IllegalArgumentException("Error getEnterprises");
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/employees")
    public ResponseEntity<EmployeesDTO> create(@Valid @RequestBody EmployeesDTO employeesDTO) throws URISyntaxException {
        log.debug("REST request to create a employees");
        Validation.IfIdExistThrowURISyntaxException(employeesDTO.getId(), ENTITY_NAME);
        try {
            EmployeesDTO result = employeesService.saveEmployees(employeesDTO);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @PutMapping("/employees")
    public ResponseEntity<EmployeesDTO> update(@Valid @RequestBody EmployeesDTO employeesDTO) throws URISyntaxException {
        log.debug("REST request to create a employees");
        Validation.IfIdNotExistThrowURISyntaxException(employeesDTO.getId(), ENTITY_NAME);
        try {
            EmployeesDTO result = employeesService.saveEmployees(employeesDTO);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id", required = true) Long id) throws URISyntaxException {
        log.debug("REST request to delete a employees");
        try {
            employeesService.deleteEmployees(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }


}
