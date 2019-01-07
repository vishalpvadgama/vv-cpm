package com.psg.carparkmanager.service.handler;

import com.psg.carparkmanager.model.Car;

import java.util.SortedMap;
import java.util.concurrent.atomic.AtomicLong;

public interface CarParkHandler {

    void handle(String action, SortedMap<Long, Car> ticketToCarMap, AtomicLong atomicTicket);

}
