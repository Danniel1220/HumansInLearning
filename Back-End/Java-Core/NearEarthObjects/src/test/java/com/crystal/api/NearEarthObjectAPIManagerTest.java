package com.crystal.api;

import org.junit.Assert;
import org.junit.Test;

public class NearEarthObjectAPIManagerTest {

    @Test
    public void should_fetch_demo_key_api_response() {
        NearEarthObjectAPIManager nearEarthObjectAPIManager = new NearEarthObjectAPIManager();
        Assert.assertFalse(nearEarthObjectAPIManager.getDemoKeyAPIResponse().equals(""));
    }
}
