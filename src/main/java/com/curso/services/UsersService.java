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
import com.curso.services.exceptions.DataIntegrityViolationException;
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

    public Users findByEmail(String email) {
        Optional<Users> obj = usersRepo.findByEmail(email);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado no sistema! E-mail: " + email));
    }

    public Users create(UsersDTO objDto) {
        objDto.setId(null);
        validaPorCPFeEmail(objDto);
        Users newObj = new Users(objDto);
        return usersRepo.save(newObj);
    }

    public Users update(UUID id, UsersDTO objDto) {
        objDto.setId(id);
        Users oldObj = findById(id);
        validaPorCPFeEmail(objDto);
        oldObj = new Users(objDto);
        return usersRepo.save(oldObj);
    }

        public void delete(UUID id) {
        Users obj = findById(id);

        if (obj.getServiceOrders().size() > 0) {
            throw new DataIntegrityViolationException("Não foi possível deletar o usuário, ele já possui ordens de serviço!");
        }

        usersRepo.deleteById(id);
    }

    private void validaPorCPFeEmail(UsersDTO objDto) {
        Optional<Users> obj = usersRepo.findByCpf(objDto.getCpf());

        if (obj.isPresent() && obj.get().getId() != objDto.getId()) {
            throw new DataIntegrityViolationException("Atenção! CPF já cadastrado no sistema!");
        }

        Optional<Users> obj2 = usersRepo.findByEmail(objDto.getEmail());

        if (obj2.isPresent() && obj2.get().getId() != objDto.getId()) {
            throw new DataIntegrityViolationException("Este e-mail já está sendo usado!");
        }
    }
}
