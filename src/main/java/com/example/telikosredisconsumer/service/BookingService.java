package com.example.telikosredisconsumer.service;

import com.example.telikosredisconsumer.entity.Booking;
import com.example.telikosredisconsumer.repository.BookingRepository;
import com.example.telikosredislibrary.CacheImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Slf4j
@Service
public class BookingService {

    @Autowired
    CacheImpl cacheImpl;

    @Autowired
    BookingRepository bookingRepository;

    @Value("${cache.entry-ttl}")
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
