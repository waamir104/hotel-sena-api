package dev.waamir.hotelsenaapi.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.waamir.hotelsenaapi.adapter.repository.IRoleMongoRepository;
import dev.waamir.hotelsenaapi.domain.enumeration.RoleName;
import dev.waamir.hotelsenaapi.domain.model.Role;
import dev.waamir.hotelsenaapi.domain.port.IRoleRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class RoleService implements IRoleRepository<Role> {
    
    private final IRoleMongoRepository roleMongoRepository;
    
    @Override
    public Role create(Role role) {
        return roleMongoRepository.insert(role);
    }

    @Override
    public Optional<Role> getById(String id) {
        return roleMongoRepository.findById(id);
    }

    @Override
    public Optional<Role> getByName(RoleName name) {
        return roleMongoRepository.findByName(name);
    }

    @Override
    public void delete(Role role) {
        roleMongoRepository.delete(role);
    }

    @Override
    public void update(Role role) {
        roleMongoRepository.save(role);
    }

    @Override
    public Page<Role> paginate(Pageable pagination) {
        return roleMongoRepository.findAll(pagination);
    }

    @Override
    public List<Role> list() {
        return roleMongoRepository.findAll();
    }
    
}
