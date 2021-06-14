package ecma.ai.lesson6_task2.controller;

import ecma.ai.lesson6_task2.Service.UserService;
import ecma.ai.lesson6_task2.payload.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/USER")
public class UserController {
    @Autowired
    UserService service;

    @GetMapping("/{id}")
    public HttpEntity<?> getone(@PathVariable Integer id) {
        return ResponseEntity.ok().body(service.getId(id));
    }

    @PostMapping
    public HttpEntity<?> addAtm(@Valid @RequestBody UserDto dto) {
        return ResponseEntity.ok().body(service.addUser(dto));
    }

    @PutMapping("/{id}")
    public HttpEntity<?> put(@Valid @RequestBody UserDto dto, @PathVariable Integer id) {
        return ResponseEntity.ok().body(service.editUser( id,dto));
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> dell(@PathVariable Integer id) {
        return ResponseEntity.ok().body(service.delete(id));
    }


}
