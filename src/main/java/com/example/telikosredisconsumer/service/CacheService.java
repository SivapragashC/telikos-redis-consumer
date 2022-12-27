package com.example.telikosredisconsumer.service;

import com.example.telikosredisconsumer.entity.Booking;
import com.example.telikosredislibrary.CacheImpl;
import org.springframework.stereotype.Service;

@Service
public class CacheService extends CacheImpl<String, Booking> {

}
