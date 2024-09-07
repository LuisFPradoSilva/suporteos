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

import com.curso.domains.ServiceOrder;
import com.curso.domains.dtos.ServiceOrderDTO;
import com.curso.services.ServiceOrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/serviceorder")
@Tag(name = "Service Order", description = "API para gerenciamento das ordens de serviço")
public class ServiceOrderResource {

    @Autowired
    private ServiceOrderService serviceOrderService;


    @GetMapping
    @Operation(summary = "Lista todas as ordens de serviço", description = "Retorna uma lista contendo todas as ordens de serviço cadastradas.")
    public ResponseEntity<List<ServiceOrderDTO>> findAll() {
        return ResponseEntity.ok().body(serviceOrderService.findAll());
    }

    @Operation(summary = "Busca ordem de serviço por ID", description = "Retorna a ordem de serviço correspondente ao ID fornecido.")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ServiceOrderDTO> findById(@PathVariable UUID id) {
        ServiceOrder obj = this.serviceOrderService.findById(id);
        return ResponseEntity.ok().body(new ServiceOrderDTO(obj));
    }

    @Operation(summary = "Cria uma nova ordem de serviço", description = "Cria uma nova ordem de serviço baseada nos dados inseridos.")
    @PostMapping
    public ResponseEntity<ServiceOrderDTO> create(@Valid @RequestBody ServiceOrderDTO objDto) {
        ServiceOrder newObj = serviceOrderService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Operation(summary = "Atualiza ordem de serviço", description = "Atualiza os dados cadastrais da ordem de serviço correspondente ao ID fornecido")
    @PutMapping(value = "/{id}")
    public ResponseEntity<ServiceOrderDTO> update(@PathVariable UUID id, @Valid @RequestBody ServiceOrderDTO objDto) {
        ServiceOrder obj = serviceOrderService.update(id, objDto);
        return ResponseEntity.ok().body(new ServiceOrderDTO(obj));
    }

    @Operation(summary = "Deleta ordem de serviço", description = "Exclui a ordem de serviço correspondente ao ID fornecido.")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ServiceOrderDTO> delete(@PathVariable UUID id) {
        serviceOrderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
