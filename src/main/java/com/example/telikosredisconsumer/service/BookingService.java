package com.example.telikosredisconsumer.service;

import com.example.telikosredisconsumer.entity.Booking;
import com.example.telikosredisconsumer.repository.BookingRepository;
import com.example.telikosredislibrary.CacheException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Slf4j
@Service
public class BookingService {

    @Autowired
    CacheService cacheService;

    @Autowired
    BookingRepository bookingRepository;

    public Mono<Booking> getBooking(String bookingId)
    {
        Mono<Booking> booking = null;

        try {
            booking=  cacheService.get(bookingId)
                    .switchIfEmpty(bookingRepository.findById(bookingId).doOnNext(b->{
                        cacheService.put(b.getBookingId() , b).subscribe(e->{
                        });
                        log.info("Getting the key {} from DB",bookingId);
                    }));
        }catch (Exception e) {
            log.error("Exception occurred while fetching data from cache {}", e.getMessage());
            throw new CacheException(e.getMessage());
        }
        return booking;
    }

    public Mono<Booking> getBookingTTL(String bookingId) {

        try {

            return cacheService.get(bookingId)
                    .switchIfEmpty(bookingRepository.findById(bookingId).doOnNext(b -> {
                        cacheService.put(b.getBookingId(), b, 220).subscribe(e -> {
                        });
                        log.info("Getting the key {} from DB", bookingId);
                    }));
        }catch (Exception e) {
            log.error("Exception occurred while fetching data from cache {}", e.getMessage());
            throw new CacheException(e.getMessage());
        }
    }


}
