package dev.waamir.hotelsenaapi.domain.port;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.waamir.hotelsenaapi.domain.model.Role;

public interface IRoleRepository<T extends Role> {
    T create(T role);
    Optional<T> getById(String id);
    Optional<T> getByName(String name);
    void delete(T role);
    void update(T role);
    Page<T> paginate(Pageable pagination);
    List<T> list();
}
