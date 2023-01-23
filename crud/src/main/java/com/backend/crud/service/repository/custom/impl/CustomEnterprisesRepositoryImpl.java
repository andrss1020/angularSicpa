package com.backend.crud.service.repository.custom.impl;

import com.backend.crud.domain.Enterprises;
import com.backend.crud.service.dto.EnterprisesDTO;
import com.backend.crud.service.enumeration.Status;
import com.backend.crud.service.repository.custom.CustomEnterprisesRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public class CustomEnterprisesRepositoryImpl implements  CustomEnterprisesRepository {

    private static final String UNSORTED = "UNSORTED";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<EnterprisesDTO> loadPages(Pageable pageable) {
        StringBuilder sqlCount = new StringBuilder("select count(distinct ee.id ) from Enterprises ee ");
        StringBuilder sqlSelect = new StringBuilder(
            " Select new com.backend.crud.service.dto.EnterprisesDTO( " +
            " ee.id, " +
            " ee.status, " +
            " ee.address, " +
            " ee.name, " +
            " ee.phone " +
            " ) " +
            " FROM Enterprises ee "
        );
        StringBuilder sql = new StringBuilder();
        sql.append(" where ee.status <> :statusDeleted");

        sqlCount.append(sql);
        sqlSelect.append(sql);

        if (!UNSORTED.equals(pageable.getSort().toString())) {
            sqlSelect.append(" order by ");
            sqlSelect.append(pageable.getSort().toString().replace(":", ""));
        }

        Query queryCount = entityManager.createQuery(sqlCount.toString());
        Query querySelect = entityManager.createQuery(sqlSelect.toString());

        querySelect.setParameter("statusDeleted", Status.DELETED);
        queryCount.setParameter("statusDeleted", Status.DELETED);

        querySelect.setFirstResult((pageable.getPageNumber()) * pageable.getPageSize());
        querySelect.setMaxResults(pageable.getPageSize());
        List<EnterprisesDTO> resultList = querySelect.getResultList();

        Long total = (Long) queryCount.getSingleResult();
        return new PageImpl<>(resultList, pageable, total.longValue());
    }

    @SuppressWarnings("unchecked")
    public Optional<Enterprises> findOne(Long id) {
        StringBuilder sqlSelect = new StringBuilder(" SELECT ee FROM Enterprises ee WHERE ee.id =:id ");
        Query querySelect = entityManager.createQuery(sqlSelect.toString());
        querySelect.setParameter("id", id);
        return querySelect.setMaxResults(1).getResultList().stream().findFirst();
    }
}
