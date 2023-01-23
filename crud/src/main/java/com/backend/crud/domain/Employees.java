package com.backend.crud.domain;

import javax.persistence.*;

import com.backend.crud.service.enumeration.Status;
import com.sun.istack.NotNull;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Objects;

@Entity
@Table(name = "enterprises")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Employees extends AbstractAuditingEntity{
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

  @Column(name = "age", nullable = false)
  private Long age;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "position", nullable = false)
  private String position;

  @Column(name = "surname", nullable = false)
  private String surname;

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

  public Long getAge() {
    return age;
  }

  public void setAge(Long age) {
    this.age = age;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Employees employees = (Employees) o;
    return Objects.equals(id, employees.id) && Objects.equals(status, employees.status) && Objects.equals(address, employees.address) && Objects.equals(age, employees.age) && Objects.equals(email, employees.email) && Objects.equals(name, employees.name) && Objects.equals(position, employees.position) && Objects.equals(surname, employees.surname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, status, address, age, email, name, position, surname);
  }

  @Override
  public String toString() {
    return "Employees{" +
      "id=" + id +
      ", status='" + status + '\'' +
      ", address='" + address + '\'' +
      ", age='" + age + '\'' +
      ", email='" + email + '\'' +
      ", name='" + name + '\'' +
      ", position='" + position + '\'' +
      ", surname='" + surname + '\'' +
      '}';
  }
}
