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

import com.curso.domains.Technician;
import com.curso.domains.dtos.TechnicianDTO;
import com.curso.services.TechnicianService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/technician")
@Tag(name = "Technician", description = "API para gerenciamento dos técnicos.")
public class TechnicianResource {

    @Autowired
    private TechnicianService techService;

    @Operation(summary = "Lista todos os técnicos", description = "Retorna uma lista contendo todos os técnicos cadastrados.")
    @GetMapping
    public ResponseEntity<List<TechnicianDTO>> findAll() {
        return ResponseEntity.ok().body(techService.findAll());
    }

    @Operation(summary = "Busca técnico por ID", description = "Retorna o técnico correspondente ao ID fornecido.")
    @GetMapping(value = "/{id}")
    public ResponseEntity<TechnicianDTO> findById(@PathVariable UUID id) {
        Technician obj = this.techService.findById(id);
        return ResponseEntity.ok().body(new TechnicianDTO(obj));
    }

    @Operation(summary = "Busca técnico por CPF", description = "Retorna o técnico correspondente ao CPF fornecido.")
    @GetMapping(value = "/cpf/{cpf}")
    public ResponseEntity<TechnicianDTO> findByCpf(@PathVariable String cpf) {
        Technician obj = this.techService.findByCpf(cpf);
        return ResponseEntity.ok().body(new TechnicianDTO(obj));
    }

    @Operation(summary = "Busca técnico por E-Mail", description = "Retorna o técnico correspondente ao E-Mail fornecido.")
    @GetMapping(value = "/email/{email}")
    public ResponseEntity<TechnicianDTO> findByEmail(@PathVariable String email) {
        Technician obj = this.techService.findByEmail(email);
        return ResponseEntity.ok().body(new TechnicianDTO(obj));
    }

    @Operation(summary = "Cria um novo ténico", description = "Cria um novo técnico baseado nos dados inseridos.")
    @PostMapping
    public ResponseEntity<TechnicianDTO> create(@Valid @RequestBody TechnicianDTO objDto) {
        Technician newObj = techService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Operation(summary = "Atualiza técnico", description = "Atualiza os dados cadastrais do técnico correspondente ao ID fornecido")
    @PutMapping(value = "/{id}")
    public ResponseEntity<TechnicianDTO> update(@PathVariable UUID id, @Valid @RequestBody TechnicianDTO objDto) {
        Technician obj = techService.update(id, objDto);
        return ResponseEntity.ok().body(new TechnicianDTO(obj));
    }

    @Operation(summary = "Deleta técnico", description = "Exclui o técnico correspondente ao ID fornecido.")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<TechnicianDTO> delete(@PathVariable UUID id) {
        techService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
