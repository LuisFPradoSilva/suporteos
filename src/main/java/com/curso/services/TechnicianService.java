package com.curso.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.domains.Technician;
import com.curso.domains.dtos.TechnicianDTO;
import com.curso.repositories.TechnicianRepository;

@Service
public class TechnicianService {

    @Autowired
    private TechnicianRepository techRepo;

    public List<TechnicianDTO> findAll() {
        return techRepo.findAll().stream().map(obj -> new TechnicianDTO(obj)).collect(Collectors.toList());
    }

    public Technician findById(UUID id) {
        Optional<Technician> obj = techRepo.findById(id);
        return obj.orElse(null);
    }
}
