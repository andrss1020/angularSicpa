package com.backend.crud.service.dto;

import com.backend.crud.service.enumeration.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * A DTO representing a enterprises_dto, with his authorities.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = { "statusAlternative" })
public class EmployeesDTO implements Serializable {

    private Long id;
    private Status status;

    private Long age;

    private String name;

    private String email;
    private String position;

    private String surname;

    // ==============| CONSTRUCTOR |==============
    public EmployeesDTO(Long id, Status status, Long age, String name, String email, String position, String surname){
      this.id = id;
      this.status =status;
      this.age =age;
      this.name =name;
      this.email =email;
      this.position = position;
      this.surname =surname;
    }

    // ==============| GETS && SETS |==============


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

  public Long getAge() {
    return age;
  }

  public void setAge(Long age) {
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
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
  public String toString() {
    return "EmployeesDTO{" +
            "id=" + id +
            ", status=" + status +
            ", age=" + age +
            ", name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", position='" + position + '\'' +
            ", surname='" + surname + '\'' +
            '}';
  }
}
