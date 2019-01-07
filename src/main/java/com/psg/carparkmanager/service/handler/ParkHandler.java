package com.psg.carparkmanager.service.handler;

import com.psg.carparkmanager.model.ActionType;
import com.psg.carparkmanager.model.Car;

import java.util.List;
import java.util.SortedMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import static com.psg.carparkmanager.model.CommonConstants.*;

public class ParkHandler implements CarParkHandler {

    public static final Character ACTION_TYPE_INITIAL = ActionType.PARK.getInitial();

    @Override
    public void handle(String action, SortedMap<Long, Car> ticketToCarMap, AtomicLong atomicTicket) {

        if (action.length() < 2) {
            return;
        }

        String licensePlate = action.substring(1).trim();

        if (ticketToCarMap.size() >= MAX_SPACE) {
            return;
        }

        boolean isCarAlreadyParked = ticketToCarMap.values().stream()
                .map(Car::getLicensePlate)
                .anyMatch(licensePlate::equalsIgnoreCase);

        if (isCarAlreadyParked) {
            return;
        }

        int nearestAvailableSpace = INITIAL_SPACE;

        if (!ticketToCarMap.isEmpty()) {

            List<Integer> allottedSpaces = ticketToCarMap.values().stream()
                    .map(Car::getSpace)
                    .sorted()
                    .collect(Collectors.toList());

            nearestAvailableSpace = findNearestAvailableSpace(allottedSpaces, 0, ticketToCarMap.size() - 1);
        }

        Car car = Car.builder()
                .licensePlate(licensePlate)
                .space(nearestAvailableSpace)
                .ticket(atomicTicket.getAndIncrement())
                .build();

        ticketToCarMap.put(car.getTicket(), car);

    }

    private int findNearestAvailableSpace(List<Integer> allottedSpaces, int start, int end) {

        if (start > end)
            return end + 1;

        if (start != allottedSpaces.get(start))
            return start;

        int mid = (start + end) / 2;

        // Left half has all elements from 0 to mid
        if (allottedSpaces.get(mid) == mid)
            return findNearestAvailableSpace(allottedSpaces, mid + 1, end);

        return findNearestAvailableSpace(allottedSpaces, start, mid);
    }
}
