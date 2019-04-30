package com.test.controller;

import com.test.controller.response.UserSearchResponse;
import com.test.model.UserModel;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUserDetails(@PathVariable Long id) {
        Optional<UserModel> userModelOptional = userService.getUser(id);
        if (userModelOptional.isPresent()) {
            return ResponseEntity.ok(userModelOptional.get());
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/find")
    public ResponseEntity<UserSearchResponse> getFindUser(@RequestParam String firstName) {
        List<UserModel> userModels = userService.getUserByFirstName(firstName);
        if (!userModels.isEmpty()) {
            UserSearchResponse userSearchResponse = new UserSearchResponse();
            userSearchResponse.setUserModels(userModels);
            return ResponseEntity.ok(userSearchResponse);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
