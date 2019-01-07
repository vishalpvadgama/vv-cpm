package com.psg.carparkmanager.service;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicLong;

import static com.psg.carparkmanager.model.CommonConstants.INITIAL_TICKET;
import static com.psg.carparkmanager.model.CommonConstants.SEPARATOR;
import static org.junit.Assert.assertEquals;

public class DefaultCarParkManagerServiceTest {

    private final DefaultCarParkManagerService target = new DefaultCarParkManagerService();

    private AtomicLong atomicTicket;

    @Before
    public void initCarParkManagerService() {

        atomicTicket = new AtomicLong(INITIAL_TICKET);
    }

    @Test
    public void testParkUnparkCompact() {

        String actual = target.manage("pABC,pXYZ,pEFG,u5000,c".split(SEPARATOR), atomicTicket);
        String expected = "XYZ,EFG,,,,,,,,";
        assertEquals(expected, actual);
    }

    @Test
    public void testParkUnpark() {

        String actual = target.manage("pABC,pXYZ,pEFG,u5000".split(SEPARATOR), atomicTicket);
        String expected = ",XYZ,EFG,,,,,,,";
        assertEquals(expected, actual);
    }

    @Test
    public void testParkUnparkPark() {

        String actual = target.manage("pABC,pXYZ,pEFG,u5001,pHIJ".split(SEPARATOR), atomicTicket);
        String expected = "ABC,HIJ,EFG,,,,,,,";
        assertEquals(expected, actual);
    }

    @Test
    public void testParkUnparkCompactPark() {

        String actual = target.manage("pABC,pXYZ,pEFG,u5001,c,pHIJ".split(SEPARATOR), atomicTicket);
        String expected = "ABC,EFG,HIJ,,,,,,,";
        assertEquals(expected, actual);
    }

}
