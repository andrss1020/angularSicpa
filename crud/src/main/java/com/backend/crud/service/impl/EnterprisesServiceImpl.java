package com.backend.crud.service.impl;

import com.backend.crud.domain.Enterprises;
import com.backend.crud.service.EnterprisesService;
import com.backend.crud.service.dto.EnterprisesDTO;
import com.backend.crud.service.enumeration.Status;
import com.backend.crud.service.mapper.EnterprisesMapper;
import com.backend.crud.service.repository.EnterprisesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EnterprisesServiceImpl implements EnterprisesService {

    private final Logger log = LoggerFactory.getLogger(EnterprisesServiceImpl.class);
    private final EnterprisesRepository enterprisesRepository;
    private EnterprisesMapper enterprisesMapper;

    public EnterprisesServiceImpl(EnterprisesMapper enterprisesMapper, EnterprisesRepository enterprisesRepository) {
        this.enterprisesMapper = enterprisesMapper;
        this.enterprisesRepository = enterprisesRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EnterprisesDTO> getEnterprises(Pageable pageable) {
      log.debug("Request to get enterprises");
      return enterprisesRepository.loadPages(pageable);
    }

    @Override
    public EnterprisesDTO save(EnterprisesDTO enterprisesDTO) {
        log.debug("Request to save a enterprisesDTO : {}", enterprisesDTO);

        if (enterprisesDTO.getId() == null) {
            enterprisesDTO.setStatus(Status.ACTIVE);
        }

        Enterprises enterprises = enterprisesMapper.toEntity(enterprisesDTO);
        enterprisesRepository.save(enterprises);
        return null;
    }

    @Override
    public EnterprisesDTO getEnterprisesById(Long enterprisesId) {
        return enterprisesRepository.getEnterprisesById(enterprisesId);
    }

    @Override
    public void deleteEnterprises(Long enterprisesId) {

    }
}
