package com.curso.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.domains.Users;
import com.curso.domains.dtos.UsersDTO;
import com.curso.repositories.UsersRepository;
import com.curso.services.exceptions.ObjectNotFoundException;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepo;

    public List<UsersDTO> findAll() {
        return usersRepo.findAll().stream().map(obj -> new UsersDTO(obj)).collect(Collectors.toList());
    }

    public Users findById(UUID id) {
        Optional<Users> obj = usersRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado no sistema! ID: " + id));
    }

    public Users findByCpf(String cpf) {
        Optional<Users> obj = usersRepo.findByCpf(cpf);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado no sistema! CPF: " + cpf));
    }

    public Users create(UsersDTO objDto) {
        objDto.setId(null);
        Users newObj = new Users(objDto);
        return usersRepo.save(newObj);
    }
}
