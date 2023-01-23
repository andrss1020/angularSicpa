package com.backend.crud.service.repository;

import com.backend.crud.domain.Enterprises;
import com.backend.crud.service.dto.EnterprisesDTO;
import com.backend.crud.service.repository.custom.CustomEnterprisesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the OrderTime entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnterprisesRepository extends JpaRepository<Enterprises, Long>, JpaSpecificationExecutor<Enterprises>,  CustomEnterprisesRepository {
    @Query(
            " Select new com.backend.crud.service.dto.EnterprisesDTO(" +
                    " ee.id, " +
                    " ee.status, " +
                    " ee.address, " +
                    " ee.name, " +
                    " ee.phone " +
                    " ) " +
                    " from Enterprises ee " +
                    " where ee.id = :enterprisesId "
    )
    EnterprisesDTO getEnterprisesById(@Param("enterprisesId") Long enterprisesId);

}
