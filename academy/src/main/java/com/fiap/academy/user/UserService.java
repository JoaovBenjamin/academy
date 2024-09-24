package com.fiap.academy.user;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class UserService {
    @Autowired
    UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> findAll(){
        return repository.findAll();
    }
    

    public ResponseEntity<EntityModel<User>> createUser(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        var newUser = repository.save(user);


        return ResponseEntity
                            .created(newUser.toEntityModel().getRequiredLink("self").toUri())
                            .body(newUser.toEntityModel());
    }

    public ResponseEntity<EntityModel<User>> putUser(@RequestParam Long Id, @RequestBody User putUser){
        User user = repository.findById(Id)
                    .orElseThrow(() -> new RuntimeException("User not found"));

        BeanUtils.copyProperties(putUser, user, "id");
        repository.save(user);

        return ResponseEntity.ok(user.toEntityModel());
    }

    public ResponseEntity<Void> deleteUser(@RequestParam Long id){
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }


}
