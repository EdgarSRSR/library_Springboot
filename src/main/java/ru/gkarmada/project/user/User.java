package ru.gkarmada.project.user;

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
public class User {

  // User object gets the information from the database
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  private Long userid;
  private String userfirstname;
  private String userlastname;
  private String usersecondname;
  private String telephone;
  private String email;
  private String roles;


  /*public User(Long userid, String userfirstname, String userlastname, String usersecondname, String telephone,
      String email,String roles){
    this.userid = userid;
    this.userfirstname = userfirstname;
    this.userlastname = userlastname;
    this.usersecondname = usersecondname;
    this.telephone = telephone;
    this.email = email;
    this.roles = roles;
  }*/

  // String Methods
  @Override
  public String toString(){
    return String.format(
        "[ userid=%d, userfirstname='%s',userlastname='%s',usersecondname='%s', telephone='%s', email='%s', roles='%s']",
        userid, userfirstname, telephone,  email, roles);
  }

  public User(String name) {
    this.userfirstname = name;
  }

}
