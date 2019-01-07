package com.psg.carparkmanager;

import com.psg.carparkmanager.service.CarParkManagerService;
import com.psg.carparkmanager.service.DefaultCarParkManagerService;

import java.util.concurrent.atomic.AtomicLong;

import static com.psg.carparkmanager.model.CommonConstants.INITIAL_TICKET;
import static com.psg.carparkmanager.model.CommonConstants.SEPARATOR;

public class CarParkManager {

    public static void main(String[] actions) {

        CarParkManagerService carParkManager = new DefaultCarParkManagerService();

        AtomicLong atomicTicket = new AtomicLong(INITIAL_TICKET);

        String result = carParkManager.manage(actions.length > 0 ? actions[0].split(SEPARATOR) : actions, atomicTicket);

        System.out.println(result);

    }
}
