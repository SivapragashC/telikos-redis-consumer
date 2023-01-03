package com.example.telikosredisconsumer.service;

import com.example.telikosredisconsumer.entity.Booking;
import com.example.telikosredisconsumer.repository.BookingRepository;
import com.example.telikosredislibrary.service.CacheImpl;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Slf4j
@Service
@ConfigurationProperties("cache")
@Setter
public class BookingService {

    @Autowired
    CacheImpl cacheImpl;

    @Autowired
    BookingRepository bookingRepository;

    private int entryTtl;

    public Mono<Booking> getBooking(String bookingId) {
        return cacheImpl.get(bookingId)
                .switchIfEmpty(bookingRepository.findById(bookingId).doOnNext(b -> {
                    cacheImpl.put(b.getBookingId(), b).subscribe(e -> {
                    });
                    log.info("Getting the key {} from DB", bookingId);
                }));
    }

    public Mono<Booking> getBookingTTL(String bookingId) {
        return cacheImpl.get(bookingId)
                .switchIfEmpty(bookingRepository.findById(bookingId).doOnNext(b -> {
                    cacheImpl.put(b.getBookingId(), b, entryTtl).subscribe(e -> {
                    });
                    log.info("Getting the key {} from DB", bookingId);
                }));
    }


}
