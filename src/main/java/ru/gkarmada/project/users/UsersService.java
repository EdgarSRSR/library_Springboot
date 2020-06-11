package ru.gkarmada.project.users;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Gets the users services dependencies
@Service
public class UsersService {

  // Methods to create a CRUD table
  @Autowired
  private UsersRepository userepo;

  // Creates a list of employees from the database
  public List<Users> listAll(){
    return userepo.findAll();
  }

  // Saves a new employee to the Database
  public void save(Users users){
    userepo.save(users);
  }

  // Get an user from id
  public Users get(Long id){
    return userepo.findById(id).get();
  }

  // Deletes employee by id
  public void delete(Long id){
    userepo.deleteById(id);
  }

}
