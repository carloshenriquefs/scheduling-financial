package com.challenge.financial.scheduling.service;

import com.challenge.financial.scheduling.dto.UserDTO;
import com.challenge.financial.scheduling.entity.User;
import com.challenge.financial.scheduling.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<UserDTO> findAll() {
        List<User> user = repository.findAll(Sort.by("name"));
        return user.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
    }

    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    public UserDTO insert(UserDTO dto) {
        User entity = new User();
        copyDtoToEntity(entity, dto);
        repository.save(entity);
        return new UserDTO(entity);
    }

    private void copyDtoToEntity(User entity, UserDTO dto) {
        entity.setName(dto.getName());
        entity.setLastName(dto.getLastName());
        entity.setAge(dto.getAge());
    }
}
