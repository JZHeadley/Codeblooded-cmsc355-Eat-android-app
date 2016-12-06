package com.jzheadley.eat.utils;

import static com.jzheadley.eat.utils.Utilities.convertMetersToMiles;

import junit.framework.Assert;

import org.junit.Test;


public class UtilitiesUnitTest {
    public UtilitiesUnitTest() {
    }

    // TDD
    @Test
    public void testConvertMetersToMiles() throws Exception {
        double meterDistance = 201168;
        double milesDistance = convertMetersToMiles(meterDistance);
        Assert.assertEquals(milesDistance, (double) 125);
    }
}