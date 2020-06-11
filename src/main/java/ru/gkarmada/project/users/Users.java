package ru.gkarmada.project.users;

//  Configures variables for getting data from the users table

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// This class declares the parameters contained in the users data base
@Entity
@Table(name="users")
public class Users {

  // Users object gets the information from the database
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name= "userid")
  private Long userid;
  private String fio;
  private String telephone;
  private String jobpos;
  private String department;
  private String email;
  private Boolean admin;

  public Users(){}

  public Users(Long userid, String fio, String telephone, String jobpos, String department,
      String email,Boolean admin){
    this.userid = userid;
    this.fio = fio;
    this.telephone = telephone;
    this.jobpos = jobpos;
    this.department = department;
    this.email = email;
    this.admin = admin;
  }

  // String Methods
  @Override
  public String toString(){
    return String.format(
        "[ userid=%d, fio='%s', telephone='%s', jobpos='%s', department='%s', email='%s', admin='%b']",
        userid, fio, telephone, jobpos, department, email, admin);
  }

  // Setters and Getters
  // Specifies that UserId is the primary key


  public Long getUserid() {
    return userid;
  }

  public void setUserid(Long userid) {
    this.userid = userid;
  }

  public String getFio() {
    return fio;
  }

  public void setFio(String fio) {
    this.fio = fio;
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public String getJobpos() {
    return jobpos;
  }

  public void setJobpos(String jobpos) {
    this.jobpos = jobpos;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Boolean getAdmin() {
    return admin;
  }

  public void setAdmin(Boolean admin) {
    this.admin = admin;
  }
}
