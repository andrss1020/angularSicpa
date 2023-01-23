package com.backend.crud.service.repository;

import com.backend.crud.domain.Employees;
import com.backend.crud.service.dto.EmployeesDTO;
import com.backend.crud.service.repository.custom.CustomEmployeesRepository;
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
public interface EmployeesRepository extends JpaRepository<Employees, Long>, JpaSpecificationExecutor<Employees>, CustomEmployeesRepository {
    @Query(
            " Select new com.backend.crud.service.dto.EmployeesDTO(" +
            " ep.id, " +
            " ep.status, " +
            " ep.age, " +
            " ep.name, " +
            " ep.email, " +
            " ep.position, " +
            " ep.surname " +
            " ) " +
            " from Employees ep " +
            " where ep.id = :employeeId "
    )
    EmployeesDTO getEmployeesById(@Param("employeeId") Long employeeId);

}
