package com.psg.carparkmanager.service.handler;

import com.psg.carparkmanager.model.ActionType;
import com.psg.carparkmanager.model.Car;

import java.util.SortedMap;
import java.util.concurrent.atomic.AtomicLong;

public class UnparkHandler implements CarParkHandler {

    public static final Character ACTION_TYPE_INITIAL = ActionType.UNPARK.getInitial();

    @Override
    public void handle(String action, SortedMap<Long, Car> ticketToCarMap, AtomicLong atomicTicket) {

        if (action.length() < 2) {
            return;
        }

        String ticketStr = action.substring(1).trim();

        Long ticket;
        try {

            ticket = Long.valueOf(ticketStr);

        } catch (NumberFormatException nfe) {
            return;
        }

        ticketToCarMap.remove(ticket);
    }
}
