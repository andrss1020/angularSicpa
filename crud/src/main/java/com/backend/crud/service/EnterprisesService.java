package com.backend.crud.service;

import com.backend.crud.service.dto.EnterprisesDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EnterprisesService {
  Page<EnterprisesDTO> getEnterprises(Pageable pageable);
  EnterprisesDTO save(EnterprisesDTO enterprisesDTO);
  EnterprisesDTO getEnterprisesById(Long enterprisesId);
  void deleteEnterprises(Long enterprisesId);
}
