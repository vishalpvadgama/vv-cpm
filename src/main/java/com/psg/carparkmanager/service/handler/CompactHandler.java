package com.psg.carparkmanager.service.handler;

import com.psg.carparkmanager.model.ActionType;
import com.psg.carparkmanager.model.Car;

import java.util.Comparator;
import java.util.SortedMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import static com.psg.carparkmanager.model.CommonConstants.INITIAL_SPACE;

public class CompactHandler implements CarParkHandler {

    public static final Character ACTION_TYPE_INITIAL = ActionType.COMPACT.getInitial();

    @Override
    public void handle(String action, SortedMap<Long, Car> ticketToCarMap, AtomicLong atomicTicket) {

        AtomicInteger atomicInteger = new AtomicInteger(INITIAL_SPACE);

        ticketToCarMap.values().stream()
                .sorted(Comparator.comparingInt(Car::getSpace))
                .forEach(car -> car.setSpace(atomicInteger.getAndIncrement()));

    }
}
