package com.curso.resources;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.curso.domains.ServiceOrder;
import com.curso.domains.dtos.ServiceOrderDTO;
import com.curso.services.ServiceOrderService;

@RestController
@RequestMapping(value = "/serviceorder")
public class ServiceOrderResource {

    @Autowired
    private ServiceOrderService serviceOrderService;

    @GetMapping
    public ResponseEntity<List<ServiceOrderDTO>> findAll() {
        return ResponseEntity.ok().body(serviceOrderService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ServiceOrderDTO> findById(@PathVariable UUID id) {
        ServiceOrder obj = this.serviceOrderService.findById(id);
        return ResponseEntity.ok().body(new ServiceOrderDTO(obj));
    }

    @PutMapping
    public ResponseEntity<ServiceOrderDTO> create(@RequestBody ServiceOrderDTO objDto) {
        ServiceOrder newObj = serviceOrderService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
