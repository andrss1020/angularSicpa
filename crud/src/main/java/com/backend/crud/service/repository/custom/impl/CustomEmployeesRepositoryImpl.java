package com.backend.crud.service.repository.custom.impl;

import com.backend.crud.service.dto.EmployeesDTO;
import com.backend.crud.service.enumeration.Status;
import com.backend.crud.service.repository.custom.CustomEmployeesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class CustomEmployeesRepositoryImpl implements CustomEmployeesRepository {

    private static final String UNSORTED = "UNSORTED";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<EmployeesDTO> loadPages(Pageable pageable, Long employeesId) {
        StringBuilder sqlCount = new StringBuilder("select count(distinct ee.id ) from Employees ee ");
        StringBuilder sqlSelect = new StringBuilder(
            " Select new com.backend.crud.service.dto.EmployeesDTO( " +
            " ep.id, " +
            " ep.status, " +
            " ep.age, " +
            " ep.name, " +
            " ep.email, " +
            " ep.position, " +
            " ep.surname " +
            " ) " +
            " FROM Employees ep "
        );
        StringBuilder sql = new StringBuilder();
        sql.append(" inner join Departments dp on dp.id = ep.departments.id ");
        sql.append(" where dp.status <> :statusDeleted and ep.departments.id = :employeesId");

        sqlCount.append(sql);
        sqlSelect.append(sql);

        if (!UNSORTED.equals(pageable.getSort().toString())) {
            sqlSelect.append(" order by ");
            sqlSelect.append(pageable.getSort().toString().replace(":", ""));
        }

        Query queryCount = entityManager.createQuery(sqlCount.toString());
        Query querySelect = entityManager.createQuery(sqlSelect.toString());
        queryCount.setParameter("employeesId", employeesId);
        querySelect.setParameter("employeesId", employeesId);
        querySelect.setParameter("statusDeleted", Status.DELETED);
        queryCount.setParameter("statusDeleted", Status.DELETED);

        querySelect.setFirstResult((pageable.getPageNumber()) * pageable.getPageSize());
        querySelect.setMaxResults(pageable.getPageSize());
        List<EmployeesDTO> resultList = querySelect.getResultList();

        Long total = (Long) queryCount.getSingleResult();
        return new PageImpl<>(resultList, pageable, total.longValue());
    }


}
