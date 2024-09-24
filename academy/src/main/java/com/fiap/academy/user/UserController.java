package com.fiap.academy.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@Controller
@RestController
@RequestMapping(name = "user")
public class UserController {
    @Autowired
    UserService service;

    @GetMapping()
    public List<User> findAll() {
        return service.findAll();
    }
    

    @PostMapping()
    public ResponseEntity<EntityModel<User>> createUser(@RequestBody User user){
        return service.createUser(user);
    }

    @PutMapping("{id}")
    public ResponseEntity<EntityModel<User>> putUser(@PathVariable Long id, @RequestBody User user) {
            return service.putUser(id, user);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        return service.deleteUser(id);
    }
    

}
