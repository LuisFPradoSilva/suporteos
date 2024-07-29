package com.curso.resources;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.domains.Users;
import com.curso.domains.dtos.UsersDTO;
import com.curso.services.UsersService;

@RestController
@RequestMapping(value = "/users")
public class UsersResource {

    @Autowired
    private UsersService usersService;

    @GetMapping
    public ResponseEntity<List<UsersDTO>> findAll() {
        return ResponseEntity.ok().body(usersService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsersDTO> findById(@PathVariable UUID id) {
        Users obj = this.usersService.findById(id);
        return ResponseEntity.ok().body(new UsersDTO(obj));
    }

    @GetMapping(value = "/cpf/{cpf}")
    public ResponseEntity<UsersDTO> findByCpf(@PathVariable String cpf) {
        Users obj = this.usersService.findByCpf(cpf);
        return ResponseEntity.ok().body(new UsersDTO(obj));
    }
}
