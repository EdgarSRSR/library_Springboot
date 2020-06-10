package ru.gkarmada.project.model;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Gets the users services dependencies
@Service
public class UsersService {

  // Methods to create a CRUD table
  @Autowired
  private UsersRepository repo;

  // Creates a list of employees from the database
  public List<Users> listAll(){
    return repo.findAll();
  }

  // Saves a new employee to the Database
  public void save(Users users){
    repo.save(users);
  }

  // Get an user from id
  public Users get(Long id){
    return repo.findById(id).get();
  }

  // Deletes employee by id
  public void delete(Long id){
    repo.deleteById(id);
  }

}
