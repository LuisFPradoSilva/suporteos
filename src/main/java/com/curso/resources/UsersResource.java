package com.curso.resources;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.curso.domains.Users;
import com.curso.domains.dtos.UsersDTO;
import com.curso.services.UsersService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/users")
@Tag(name = "Users", description = "API para gerenciamento dos usuários")
public class UsersResource {

    @Autowired
    private UsersService usersService;

    @Operation(summary = "Lista todos os usuários", description = "Retorna uma lista contendo todos os usuários cadastrados.")
    @GetMapping
    public ResponseEntity<List<UsersDTO>> findAll() {
        return ResponseEntity.ok().body(usersService.findAll());
    }

    @Operation(summary = "Busca usuário por ID", description = "Retorna o usuário correspondente ao ID fornecido.")
    @GetMapping(value = "/{id}")
    public ResponseEntity<UsersDTO> findById(@PathVariable UUID id) {
        Users obj = this.usersService.findById(id);
        return ResponseEntity.ok().body(new UsersDTO(obj));
    }

    @Operation(summary = "Busca usuário por CPF", description = "Retorna o usuário correspondente ao CPF fornecido.")
    @GetMapping(value = "/cpf/{cpf}")
    public ResponseEntity<UsersDTO> findByCpf(@PathVariable String cpf) {
        Users obj = this.usersService.findByCpf(cpf);
        return ResponseEntity.ok().body(new UsersDTO(obj));
    }

    @Operation(summary = "Busca usuário por E-Mail", description = "Retorna o usuário correspondente ao E-Mail fornecido.")
    @GetMapping(value = "/email/{email}")
    public ResponseEntity<UsersDTO> findByEmail(@PathVariable String email) {
        Users obj = this.usersService.findByEmail(email);
        return ResponseEntity.ok().body(new UsersDTO(obj));
    }

    @Operation(summary = "Cria um novo usuário", description = "Cria um novo usuário baseado nos dados inseridos.")
    @PostMapping
    public ResponseEntity<UsersDTO> create(@Valid @RequestBody UsersDTO objDto) {
        Users newObj = usersService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Operation(summary = "Atualiza usuário", description = "Atualiza os dados cadastrais do usuário correspondente ao ID fornecido")
    @PutMapping(value = "/{id}")
    public ResponseEntity<UsersDTO> update(@PathVariable UUID id, @Valid @RequestBody UsersDTO objDto) {
        Users obj = usersService.update(id, objDto);
        return ResponseEntity.ok().body(new UsersDTO(obj));
    }

    @Operation(summary = "Deleta usuário", description = "Exclui o usuário correspondente ao ID fornecido.")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UsersDTO> delete(@PathVariable UUID id) {
        usersService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
