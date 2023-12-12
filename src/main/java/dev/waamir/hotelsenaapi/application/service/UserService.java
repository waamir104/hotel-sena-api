package dev.waamir.hotelsenaapi.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.waamir.hotelsenaapi.adapter.repository.IUserMongoRepository;
import dev.waamir.hotelsenaapi.domain.model.User;
import dev.waamir.hotelsenaapi.domain.port.IUserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUserRepository<User> {

    private final IUserMongoRepository userMongoRepository;

    @Override
    public User create(User user) {
        return userMongoRepository.insert(user);
    }

    @Override
    public Optional<User> getById(String id) {
        return userMongoRepository.findById(id);
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return userMongoRepository.findByUsername(username);
    }

    @Override
    public void delete(User user) {
        userMongoRepository.delete(user);
    }

    @Override
    public void update(User user) {
        userMongoRepository.save(user);
    }

    @Override
    public Page<User> paginate(Pageable pagination) {
        return userMongoRepository.findAll(pagination);
    }

    @Override
    public List<User> list() {
        return userMongoRepository.findAll();
    }

    @Override
    public Integer getUsernameCount(String username) {
        return userMongoRepository.countByUsername(username);
    }

}
