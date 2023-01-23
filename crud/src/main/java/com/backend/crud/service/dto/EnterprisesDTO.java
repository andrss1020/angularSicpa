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
public class EnterprisesDTO implements Serializable {

    private Long id;
    private Status status;

    private String address;

    private String name;

    private String phone;

    // ==============| CONSTRUCTOR |==============
    public EnterprisesDTO(Long id, Status status, String address, String name, String phone){
      this.id = id;
      this.status =status;
      this.address =address;
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
  public String toString() {
    return "EnterprisesDTO{" +
      "id=" + id +
      ", status=" + status +
      ", address=" + address +
      ", name=" + name +
      ", phone=" + phone +
      '}';
  }
}
