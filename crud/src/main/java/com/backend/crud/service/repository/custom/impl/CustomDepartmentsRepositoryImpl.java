package com.backend.crud.service.repository.custom.impl;

import com.backend.crud.domain.Departments;
import com.backend.crud.service.dto.DepartmentsDTO;
import com.backend.crud.service.enumeration.Status;
import com.backend.crud.service.repository.custom.CustomDepartmentsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class CustomDepartmentsRepositoryImpl implements CustomDepartmentsRepository {

    private static final String UNSORTED = "UNSORTED";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<DepartmentsDTO> loadPages(Pageable pageable, Long enterpriseId) {
        StringBuilder sqlCount = new StringBuilder("select count(distinct dp.id ) from Departments dp ");
        StringBuilder sqlSelect = new StringBuilder(
            " Select new com.backend.crud.service.dto.DepartmentsDTO( " +
            " dp.id, " +
            " dp.status, " +
            " dp.description, " +
            " dp.name, " +
            " dp.phone " +
            " ) " +
            " FROM Departments dp "
        );
        StringBuilder sql = new StringBuilder();
        sql.append(" inner join Enterprises ee on ee.id = dp.enterprises.id ");
        sql.append(" where dp.status <> :statusDeleted and dp.enterprises.id = :enterpriseId");

        sqlCount.append(sql);
        sqlSelect.append(sql);

        if (!UNSORTED.equals(pageable.getSort().toString())) {
            sqlSelect.append(" order by ");
            sqlSelect.append(pageable.getSort().toString().replace(":", ""));
        }

        Query queryCount = entityManager.createQuery(sqlCount.toString());
        Query querySelect = entityManager.createQuery(sqlSelect.toString());

        queryCount.setParameter("enterpriseId", enterpriseId);
        querySelect.setParameter("enterpriseId", enterpriseId);
        querySelect.setParameter("statusDeleted", Status.DELETED);
        queryCount.setParameter("statusDeleted", Status.DELETED);

        querySelect.setFirstResult((pageable.getPageNumber()) * pageable.getPageSize());
        querySelect.setMaxResults(pageable.getPageSize());
        List<DepartmentsDTO> resultList = querySelect.getResultList();

        Long total = (Long) queryCount.getSingleResult();
        return new PageImpl<>(resultList, pageable, total.longValue());
    }

    @SuppressWarnings("unchecked")
    public Optional<Departments> findOne(Long id) {
        StringBuilder sqlSelect = new StringBuilder(" SELECT dp FROM Departments dp WHERE dp.id =:id ");
        Query querySelect = entityManager.createQuery(sqlSelect.toString());
        querySelect.setParameter("id", id);
        return querySelect.setMaxResults(1).getResultList().stream().findFirst();
    }
}
