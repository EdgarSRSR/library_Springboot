package ru.gkarmada.project.users;

//  Configures variables for getting data from the users table

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// This class declares the parameters contained in the users data base
@Entity
// lombok implementation
@Getter  @Setter @NoArgsConstructor // <--- THIS is it
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

}
