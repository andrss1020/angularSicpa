package com.backend.crud.service.repository.custom;

import com.backend.crud.domain.Enterprises;
import com.backend.crud.service.dto.EnterprisesDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CustomEnterprisesRepository {
    Page<EnterprisesDTO> loadPages(Pageable pageable);

    Optional<Enterprises> findOne(Long id);
}
