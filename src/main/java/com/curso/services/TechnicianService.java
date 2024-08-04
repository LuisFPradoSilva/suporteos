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
import com.curso.services.exceptions.DataIntegrityViolationException;
import com.curso.services.exceptions.ObjectNotFoundException;

@Service
public class TechnicianService {

    @Autowired
    private TechnicianRepository techRepo;

    public List<TechnicianDTO> findAll() {
        return techRepo.findAll().stream().map(obj -> new TechnicianDTO(obj)).collect(Collectors.toList());
    }

    public Technician findById(UUID id) {
        Optional<Technician> obj = techRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Técnico não encontrado no sistema! ID: " + id));
    }

    public Technician findByCpf(String cpf) {
        Optional<Technician> obj = techRepo.findByCpf(cpf);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Técnico não encontrado no sistema! CPF: " + cpf));
    }

    public Technician findByEmail(String email) {
        Optional<Technician> obj = techRepo.findByEmail(email);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Técnico não encontrado no sistema! E-mail: " + email));
    }

    public Technician create(TechnicianDTO objDto) {
        objDto.setId(null);
        validaPorCPFeEmail(objDto);
        Technician newObj = new Technician(objDto);
        return techRepo.save(newObj);
    }

    private void validaPorCPFeEmail(TechnicianDTO objDto) {
        Optional<Technician> obj = techRepo.findByCpf(objDto.getCpf());

        if (obj.isPresent() && obj.get().getId() != objDto.getId()) {
            throw new DataIntegrityViolationException("Atenção! CPF já cadastrado no sistema!");
        }

        Optional<Technician> obj2 = techRepo.findByEmail(objDto.getEmail());

        if (obj2.isPresent() && obj2.get().getId() != objDto.getId()) {
            throw new DataIntegrityViolationException("Este e-mail já está sendo usado!");
        }
    }
}
