package com.backend.crud.domain;

import com.backend.crud.service.enumeration.Status;
import javax.persistence.*;

import com.sun.istack.NotNull;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Objects;

@Entity
@Table(name = "enterprises")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Enterprises extends AbstractAuditingEntity{
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false)
  private Status status;

  @Column(name = "address", nullable = false)
  private String address;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "phone", nullable = false)
  private String phone;

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

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Enterprises that = (Enterprises) o;
    return Objects.equals(id, that.id) && Objects.equals(status, that.status) && Objects.equals(address, that.address) && Objects.equals(name, that.name) && Objects.equals(phone, that.phone);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, status, address, name, phone);
  }

  @Override
  public String toString() {
    return "Enterprises{" +
      "id=" + id +
      ", status='" + status + '\'' +
      ", address='" + address + '\'' +
      ", name='" + name + '\'' +
      ", phone='" + phone + '\'' +
      '}';
  }
}
