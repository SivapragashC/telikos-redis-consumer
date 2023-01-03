package com.example.telikosredisconsumer.controller;

import com.example.telikosredisconsumer.entity.Booking;
import com.example.telikosredisconsumer.service.BookingService;
import com.example.telikosredislibrary.exception.CacheException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("booking")
@Slf4j
public class BookingController {

    @Autowired
    BookingService bookingService;

    @GetMapping("/{bookingId}")
    public Mono<ResponseEntity<Booking>> getBooking(@PathVariable String bookingId) {
        return this.bookingService.getBooking(bookingId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .doOnError(e ->
                {
                    log.error("In controller {}", e.getMessage());
                    throw new CacheException(e.getMessage());
                });
    }

    @GetMapping("/entryTTL/{bookingId}")
    public Mono<ResponseEntity<Booking>> getBookingTTL(@PathVariable String bookingId) {
        return this.bookingService.getBookingTTL(bookingId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .doOnError(e ->
                {
                    log.error("In controller {}", e.getMessage());
                    throw new CacheException(e.getMessage());
                });
    }
}
