package com.backend.crud.controller;

import com.backend.crud.service.DepartmentsService;
import com.backend.crud.service.dto.DepartmentsDTO;
import com.backend.crud.service.repository.DepartmentsRepository;
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
public class DepartmentsController {
    private final Logger log = LoggerFactory.getLogger(DepartmentsController.class);

    private static final String ENTITY_NAME = "departments";

    @Autowired
    private final DepartmentsService departmentsService;

    public DepartmentsController(DepartmentsService departmentsService) {
        this.departmentsService = departmentsService;
    }

    @GetMapping("/departments/load-page/{enterpriseId}")
    public ResponseEntity<List<DepartmentsDTO>> getDepartmentLoadPage(@PathVariable Long enterpriseId, Pageable pageable) {
      log.debug("REST request to get getEnterprisesLoadPage");
      Page<DepartmentsDTO> page = departmentsService.getDepartments(pageable, enterpriseId);
      HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
      return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/departments/{id}")
    public ResponseEntity<DepartmentsDTO> getEnterprisesById(@PathVariable(value = "id", required = true) Long enterpriseId) throws URISyntaxException {
        log.debug("REST request to get getEnterprises by id : {}", enterpriseId);
        DepartmentsDTO result = departmentsService.getDepartmentById(enterpriseId);
        if (result == null) {
            throw new IllegalArgumentException("Error getEnterprises");
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/departments")
    public ResponseEntity<DepartmentsDTO> create(@Valid @RequestBody DepartmentsDTO departmentsDTO) throws URISyntaxException {
        log.debug("REST request to create a enterprises");
        Validation.IfIdExistThrowURISyntaxException(departmentsDTO.getId(), ENTITY_NAME);
        try {
            DepartmentsDTO result = departmentsService.saveDepartments(departmentsDTO);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @PutMapping("/departments")
    public ResponseEntity<DepartmentsDTO> update(@Valid @RequestBody DepartmentsDTO departmentsDTO) throws URISyntaxException {
        log.debug("REST request to create a enterprises");
        Validation.IfIdNotExistThrowURISyntaxException(departmentsDTO.getId(), ENTITY_NAME);
        try {
            DepartmentsDTO result = departmentsService.saveDepartments(departmentsDTO);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @DeleteMapping("/departments/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id", required = true) Long id) throws URISyntaxException {
        log.debug("REST request to delete a enterprises");
        try {
            departmentsService.deleteDepartments(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }


}
