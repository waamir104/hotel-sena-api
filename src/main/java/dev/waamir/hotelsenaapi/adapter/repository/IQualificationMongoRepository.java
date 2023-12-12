package dev.waamir.hotelsenaapi.adapter.repository;

import dev.waamir.hotelsenaapi.domain.model.Qualification;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import dev.waamir.hotelsenaapi.domain.model.Guest;

@Repository
public interface IQualificationMongoRepository extends MongoRepository<Qualification,String> {

    Page<Qualification> findByGuest(Guest guest, Pageable pagination);
    long countByStars(Integer stars);
    @Query("{ $group : { '_id' : null, averageValue : { $avg : '$stars' } } }")
    double fetchRatingAverage();
}
