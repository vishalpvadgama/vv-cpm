package com.psg.carparkmanager.service;

import com.psg.carparkmanager.model.Car;
import com.psg.carparkmanager.service.handler.CarParkHandler;
import com.psg.carparkmanager.service.handler.CompactHandler;
import com.psg.carparkmanager.service.handler.ParkHandler;
import com.psg.carparkmanager.service.handler.UnparkHandler;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import static com.psg.carparkmanager.model.CommonConstants.*;

public class DefaultCarParkManagerService implements CarParkManagerService {

    public String manage(String[] actions, AtomicLong atomicTicket) {

        SortedMap<Long, Car> ticketToCarMap = new TreeMap<>();

        if (actions != null && actions.length > 0) {

            Map<Character, CarParkHandler> actionToHandlersMap = getActionToHandlersMap();

            for (String action : actions) {

                action = action.trim();

                if (action.length() < 1) {
                    continue;
                }

                char actionInitial = action.charAt(0);

                if (actionToHandlersMap.containsKey(actionInitial)) {

                    actionToHandlersMap.get(actionInitial).handle(action, ticketToCarMap, atomicTicket);
                }
            }
        }

        return getResult(ticketToCarMap);

    }


    private Map<Character, CarParkHandler> getActionToHandlersMap() {

        Map<Character, CarParkHandler> actionToHandlersMap = new HashMap<>();

        actionToHandlersMap.put(ParkHandler.ACTION_TYPE_INITIAL, new ParkHandler());
        actionToHandlersMap.put(UnparkHandler.ACTION_TYPE_INITIAL, new UnparkHandler());
        actionToHandlersMap.put(CompactHandler.ACTION_TYPE_INITIAL, new CompactHandler());

        return actionToHandlersMap;
    }

    private String getResult(SortedMap<Long, Car> ticketToCarMap) {

        AtomicInteger atomicInteger = new AtomicInteger(INITIAL_SPACE);

        StringBuilder result = new StringBuilder();

        ticketToCarMap.values().stream()
                .sorted(Comparator.comparingInt(Car::getSpace))
                .forEach(car -> {

                    while (car.getSpace() > atomicInteger.get()) {
                        result.append(SEPARATOR);
                        atomicInteger.getAndIncrement();
                    }

                    result.append(car.getLicensePlate());
                    atomicInteger.getAndIncrement();

                    if (atomicInteger.get() <= MAX_SPACE - 1) {
                        result.append(SEPARATOR);
                    }
                });

        while (atomicInteger.getAndIncrement() <= MAX_SPACE - 2) {
            result.append(SEPARATOR);
        }

        return result.toString();

    }
}
