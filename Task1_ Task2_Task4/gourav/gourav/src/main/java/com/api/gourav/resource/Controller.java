package com.api.gourav.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.api.gourav.exception.*;
import com.api.gourav.model.gourav;

import org.springframework.web.bind.annotation.ExceptionHandler;

import com.api.gourav.repository.GouravRepository;
@RestController
public class Controller {

	
	
@Autowired
private GouravRepository repository;


// Below Line of code is Used to save the data resource using (/save)

@PostMapping("/save")
@ResponseStatus(HttpStatus.CREATED)
public String save(@RequestBody gourav vishal )
{
  Optional<gourav> user=repository.findById(vishal.getId());
  if(user.isPresent())
  {
	  
	  return " Record Already Exist With Id ::"+vishal.getId();
	  
  }
	repository.save(vishal);
	return "New Record is Added Successfull With Id is::"+ vishal.getId();

	
}
@GetMapping("/findAll")
public List<gourav> findAll()
{
	List<gourav>lst=repository.findAll();
	return lst;
	
		

}
@DeleteMapping("/delete/{id}")
public String deletedetails(@PathVariable String id)
{
	Optional<gourav>user = repository.findById(id);
	if(!user.isPresent())
	{
		throw new ResourceNotFoundException("Hello Gourav !! Resource Not Found with Given Id");  
		
		
	}
	repository.deleteById((id));
	return "The Document has been Deleted with Id::"+id;	
	
	
}
@GetMapping("/findById/{id}")
public Optional<gourav> getDetailsById(@PathVariable String id) {
    Optional<gourav> user = repository.findById(id);
    if (!user.isPresent()) {
        throw new ResourceNotFoundException("Resource Not Found with Given Id: " + id);
    }
    return user;
}

@PutMapping("/update/{id}")
public String updateDetails(@PathVariable String id, @RequestBody gourav updatedUser) {
    Optional<gourav> existingUser = repository.findById(id);
    if (!existingUser.isPresent()) {
        throw new ResourceNotFoundException("Resource Not Found with Given Id: " + id);
    }
    
    gourav userToUpdate = existingUser.get();
    userToUpdate.setName(updatedUser.getName());
    userToUpdate.setFramework(updatedUser.getFramework());
    userToUpdate.setLanguage(updatedUser.getLanguage());

    repository.save(userToUpdate);

    return "The Document has been Updated with Id: " + id;
}

}
