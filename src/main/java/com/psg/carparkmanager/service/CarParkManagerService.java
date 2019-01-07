package com.psg.carparkmanager.service;

import java.util.concurrent.atomic.AtomicLong;

public interface CarParkManagerService {

    String manage(String[] args, AtomicLong atomicTicket);
}
