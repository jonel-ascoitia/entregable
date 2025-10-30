package com.example.senati.service;

import com.example.senati.model.Response;
import com.example.senati.model.User;
import com.example.senati.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public ResponseEntity<User> getUserById(int id){
        Optional<User> user = userRepository.findById(id);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<User> newUser(User user){
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
    }

    public Response deleteUser(int id){
        Optional<User> optionalUser = userRepository.findById(id);
        Response response = new Response();

        if (optionalUser.isPresent()){
            userRepository.deleteById(id);
            response.setCode(200);
            response.setStastus("Success");
            response.setMeseng("El usuario se eliminó correctamente: " + id);
            return response;
        }

        response.setCode(400);
        response.setStastus("Error");
        response.setMeseng("El usuario no existe: " + id);
        return response;
    }

    public ResponseEntity<User> updateUser(int id, User user){
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User updateUser = userOptional.get();
            updateUser.setName(user.getName());
            updateUser.setLastname(user.getLastname());
            return new ResponseEntity<>(userRepository.save(updateUser), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
