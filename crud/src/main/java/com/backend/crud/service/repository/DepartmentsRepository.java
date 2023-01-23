package com.backend.crud.service.repository;

import com.backend.crud.domain.Departments;
import com.backend.crud.service.dto.DepartmentsDTO;
import com.backend.crud.service.repository.custom.CustomDepartmentsRepository;
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
public interface DepartmentsRepository extends JpaRepository<Departments, Long>, JpaSpecificationExecutor<Departments>, CustomDepartmentsRepository {
    @Query(
            " Select new com.backend.crud.service.dto.DepartmentsDTO(" +
                    " dp.id, " +
                    " dp.status, " +
                    " dp.description, " +
                    " dp.name, " +
                    " dp.phone " +
                    " ) " +
                    " from Departments dp " +
                    " where dp.id = :departmentId "
    )
    DepartmentsDTO getDepartmentById(@Param("departmentId") Long departmentId);

}
