package com.backend.crud.service.dto;

import com.backend.crud.service.enumeration.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO representing a enterprises_dto, with his authorities.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = { "statusAlternative" })
public class DepartmentsDTO implements Serializable {

    private Long id;
    private Status status;

    private String description;

    private String name;

    private String phone;

    @NotNull
    private Long enterprisesId;

    // ==============| CONSTRUCTOR |==============

    public DepartmentsDTO() {}
    public DepartmentsDTO(Long id, Status status, String description, String name, String phone){
      this.id = id;
      this.status =status;
      this.description =description;
      this.name =name;
      this.phone =phone;
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

  public Long getEnterprisesId() {
    return enterprisesId;
  }

  public void setEnterprisesId(Long enterprisesId) {
    this.enterprisesId = enterprisesId;
  }

  @Override
  public String toString() {
    return "DepartmentsDTO{" +
      "id=" + id +
      ", status=" + status +
      ", description=" + description +
      ", name=" + name +
      ", phone=" + phone +
      '}';
  }
}
