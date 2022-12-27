package com.example.telikosredisconsumer.repository;


import com.example.telikosredisconsumer.entity.Booking;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends ReactiveCrudRepository<Booking, String> {
}
