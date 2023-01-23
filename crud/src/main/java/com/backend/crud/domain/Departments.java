package com.backend.crud.domain;

import javax.persistence.*;

import com.backend.crud.service.enumeration.Status;
import com.sun.istack.NotNull;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Objects;

@Entity
@Table(name = "departments")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Departments extends AbstractAuditingEntity{
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false)
  private Status status;

  @Column(name = "description", nullable = false)
  private String description;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "phone", nullable = false)
  private String phone;

  @ManyToOne(fetch = FetchType.LAZY)
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  private Enterprises enterprises;

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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Enterprises getEnterprises() {
    return enterprises;
  }

  public void setEnterprises(Enterprises enterprises) {
    this.enterprises = enterprises;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Departments that = (Departments) o;
    return Objects.equals(id, that.id) && Objects.equals(status, that.status) && Objects.equals(description, that.description) && Objects.equals(name, that.name) && Objects.equals(phone, that.phone) && Objects.equals(enterprises, that.enterprises);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, status, description, name, phone, enterprises);
  }

  @Override
  public String toString() {
    return "Departments{" +
      "id=" + id +
      ", status='" + status + '\'' +
      ", description='" + description + '\'' +
      ", name='" + name + '\'' +
      ", phone='" + phone + '\'' +
      ", enterprises=" + enterprises +
      '}';
  }
}
