package com.example.taskFlow.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.taskFlow.service.UserService;
import com.example.taskFlow.entity.User;
import com.example.taskFlow.dto.UserDTO;

@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> partialUpdateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        User user = userService.findbyId(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        if (userDTO.getFirstName() != null) {
            user.setFirstName(userDTO.getFirstName());
        }
        if (userDTO.getLastName() != null) {
            user.setLastName(userDTO.getLastName());
        }
        if (userDTO.getEmail() != null) {
            user.setEmail(userDTO.getEmail());
        }

        User updatedUser = userService.update(user);

        UserDTO responseDTO = new UserDTO();
        responseDTO.setId(updatedUser.getId());
        responseDTO.setFirstName(updatedUser.getFirstName());
        responseDTO.setLastName(updatedUser.getLastName());
        responseDTO.setEmail(updatedUser.getEmail());

        return ResponseEntity.ok(responseDTO);
    }
}
