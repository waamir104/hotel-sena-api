package dev.waamir.hotelsenaapi.domain.port;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.waamir.hotelsenaapi.domain.model.User;

public interface IUserRepository<T extends User> {

    T create(T user);
    Optional<T> getById(String id);
    Optional<T> getByUsername(String username);
    void delete(T user);
    void update(T user);
    Page<T> paginate(Pageable pagination);
    List<T> list();
    Integer getUsernameCount(String username);
}
