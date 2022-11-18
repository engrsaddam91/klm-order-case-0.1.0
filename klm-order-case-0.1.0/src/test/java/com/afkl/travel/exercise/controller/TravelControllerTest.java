package com.afkl.travel.exercise.controller;


import com.afkl.travel.exercise.dto.LocationDto;
import com.afkl.travel.exercise.entity.LocationType;
import com.afkl.travel.exercise.service.TravelService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.mockito.Mockito.*;

public class TravelControllerTest {

    private TravelService travelService;
    private TravelController travelController;

    @Before
    public void setUp() {
        travelService = mock(TravelService.class);
        travelController = new TravelController(travelService);
    }

    @Test
    public void testFetchLocationsByLanguage() {
        LocationDto locationDto = LocationDto.builder()
                .code("US")
                .type(LocationType.country)
                .latitude(123.36)
                .longitude(145.96)
                .build();
        when(travelService.getLocations("NL")).thenReturn(Set.of(locationDto));
        Set<LocationDto> locations = travelController.fetchLocationsByLanguage("NL");
        Assert.assertNotNull(locations);
        Assert.assertEquals(1, locations.size());

        verify(travelService).getLocations("NL");
    }

    @Test
    public void testFetchLocationsByTypeAndCode() {
        LocationDto locationDto = LocationDto.builder()
                .code("US")
                .type(LocationType.country)
                .latitude(123.36)
                .longitude(145.96)
                .build();
        when(travelService.getLocation(LocationType.country, "US", "NL")).thenReturn(locationDto);
        LocationDto result = travelController.fetchLocationsByTypeAndCode(LocationType.country, "US", "NL");
        Assert.assertNotNull(result);
        Assert.assertEquals("US", result.getCode());
        Assert.assertEquals(LocationType.country, result.getType());

        verify(travelService).getLocation(LocationType.country, "US", "NL");
    }
}
