package dev.waamir.hotelsenaapi.application.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.waamir.hotelsenaapi.adapter.repository.IWorkerMongoRepository;
import dev.waamir.hotelsenaapi.domain.model.Worker;
import dev.waamir.hotelsenaapi.domain.port.IWorkerRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class WorkerService implements IWorkerRepository<Worker> {
    
    private final IWorkerMongoRepository workerMongoRepository;
    
    @Override
    public Worker create(Worker worker) {
        return workerMongoRepository.insert(worker);
    }

    @Override
    public Optional<Worker> getById(ObjectId id) {
        return workerMongoRepository.findById(id);
    }

    @Override
    public Optional<Worker> getByEmail(String email) {
        return workerMongoRepository.findByEmail(email);
    }

    @Override
    public Optional<Worker> getByDocNumber(Long docNumber) {
        return workerMongoRepository.findByDocNumber(docNumber);
    }

    @Override
    public long countByEmail(String email) {
        return workerMongoRepository.countByEmail(email);
    }

    @Override
    public void delete(Worker worker) {
        workerMongoRepository.delete(worker);
    }

    @Override
    public void update(Worker worker) {
        workerMongoRepository.save(worker);
    }

    @Override
    public Page<Worker> list(Pageable pagination) {
        return workerMongoRepository.findAll(pagination);
    }

    @Override
    public List<Worker> list() {
        return workerMongoRepository.findAll();
    }
    
}
