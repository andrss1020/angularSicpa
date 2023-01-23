package com.backend.crud.domain;

import javax.persistence.*;

import com.backend.crud.service.enumeration.Status;
import com.sun.istack.NotNull;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Objects;

@Entity
@Table(name = "departments_employees")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DepartmentsEmployees extends AbstractAuditingEntity{
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false)
  private Status status;

  @ManyToOne(fetch = FetchType.LAZY)
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  private Departments departments;

  @ManyToOne(fetch = FetchType.LAZY)
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  private Employees employees;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public Departments getDepartments() {
    return departments;
  }

  public void setDepartments(Departments departments) {
    this.departments = departments;
  }

  public Employees getEmployees() {
    return employees;
  }

  public void setEmployees(Employees employees) {
    this.employees = employees;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DepartmentsEmployees that = (DepartmentsEmployees) o;
    return Objects.equals(id, that.id) && Objects.equals(status, that.status) && Objects.equals(departments, that.departments) && Objects.equals(employees, that.employees);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, status, departments, employees);
  }

  @Override
  public String toString() {
    return "DepartmentsEmployees{" +
      "id=" + id +
      ", status='" + status + '\'' +
      ", departments=" + departments +
      ", employees=" + employees +
      '}';
  }
}
