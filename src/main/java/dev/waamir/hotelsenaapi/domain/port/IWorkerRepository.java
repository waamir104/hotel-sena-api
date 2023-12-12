package dev.waamir.hotelsenaapi.domain.port;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.waamir.hotelsenaapi.domain.model.Worker;

public interface IWorkerRepository<T extends Worker> {
    T create(T worker);
    Optional<T> getById(String id);
    Optional<T> getByEmail(String email);
    Optional<T> getByDocNumber(Long docNumber);
    long countByEmail(String email);
    void delete(T worker);
    void update(T worker);
    Page<T> list(Pageable pagination);
    List<T> list();
}
