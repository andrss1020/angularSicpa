package com.backend.crud.controller;

import com.backend.crud.service.EnterprisesService;
import com.backend.crud.service.dto.EnterprisesDTO;
import com.backend.crud.service.repository.EnterprisesRepository;
import com.backend.crud.util.PaginationUtil;
import com.backend.crud.util.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URISyntaxException;
import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api",
        produces = "application/json")
public class EnterprisesController {
    private final Logger log = LoggerFactory.getLogger(EnterprisesController.class);

    private static final String ENTITY_NAME = "enterprises";

    @Autowired
    private final EnterprisesService enterprisesService;
    public EnterprisesController(EnterprisesService enterprisesService) {
        this.enterprisesService = enterprisesService;
    }

    @GetMapping("/enterprises/load-page")
    public ResponseEntity<List<EnterprisesDTO>> getEnterprisesLoadPage(Pageable pageable) {
      log.debug("REST request to get getEnterprisesLoadPage");
      Page<EnterprisesDTO> page = enterprisesService.getEnterprises(pageable);
      HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
      return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/enterprises/{id}")
    public ResponseEntity<EnterprisesDTO> getEnterprisesById(@PathVariable(value = "id", required = true) Long enterpriseId) throws URISyntaxException {
        log.debug("REST request to get getEnterprises by id : {}", enterpriseId);
        EnterprisesDTO result = enterprisesService.getEnterprisesById(enterpriseId);
        if (result == null) {
            throw new IllegalArgumentException("Error getEnterprises");
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/enterprises")
    public ResponseEntity<EnterprisesDTO> create(@Valid @RequestBody EnterprisesDTO enterprisesDTO) throws URISyntaxException {
        log.debug("REST request to create a enterprises");
        Validation.IfIdExistThrowURISyntaxException(enterprisesDTO.getId(), ENTITY_NAME);
        try {
            EnterprisesDTO result = enterprisesService.save(enterprisesDTO);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @PutMapping("/enterprises")
    public ResponseEntity<EnterprisesDTO> update(@Valid @RequestBody EnterprisesDTO enterprisesDTO) throws URISyntaxException {
        log.debug("REST request to create a enterprises");
        Validation.IfIdNotExistThrowURISyntaxException(enterprisesDTO.getId(), ENTITY_NAME);
        try {
            EnterprisesDTO result = enterprisesService.save(enterprisesDTO);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @DeleteMapping("/enterprises/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id", required = true) Long id) throws URISyntaxException {
        log.debug("REST request to delete a enterprises");
        try {
            enterprisesService.deleteEnterprises(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }


}
