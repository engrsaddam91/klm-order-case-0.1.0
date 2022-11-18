package com.afkl.travel.exercise.service;


import com.afkl.travel.exercise.dto.LocationDto;
import com.afkl.travel.exercise.entity.Location;
import com.afkl.travel.exercise.entity.LocationType;
import com.afkl.travel.exercise.entity.Translation;
import com.afkl.travel.exercise.repository.LocationRepository;
import com.afkl.travel.exercise.repository.TranslationRepository;
import com.afkl.travel.exercise.service.impl.TravelServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.mockito.Mockito.*;

public class TravelServiceTest {

    private TranslationRepository translationRepository;
    private LocationRepository locationRepository;
    private TravelService travelService;

    @Before
    public void setUp() {
        translationRepository = mock(TranslationRepository.class);
        locationRepository = mock(LocationRepository.class);
        travelService = new TravelServiceImpl(translationRepository, locationRepository);
    }

    @Test
    public void testGetLocations() {
        Translation translation = Translation.builder().id(1234).build();
        Location location = Location.builder().id(1234).build();
        translation.setLocation(location);
        when(translationRepository.findAllByLanguage("NL")).thenReturn(Set.of(translation));
        Set<LocationDto> locations = travelService.getLocations("NL");
        Assert.assertNotNull(locations);
        Assert.assertEquals(1, locations.size());

        verify(translationRepository).findAllByLanguage("NL");

    }

    @Test
    public void testGetLocation() {
        Translation translation = Translation.builder().id(1234).language("NL").build();
        Location location = Location.builder().id(1234).code("US").type(LocationType.country).build();
        location.setTranslations(Set.of(translation));
        when(locationRepository.findByTypeAndCode(LocationType.country, "US")).thenReturn(location);
        LocationDto locationDto = travelService.getLocation(LocationType.country, "US", "NL");

        Assert.assertNotNull(locationDto);
        Assert.assertEquals("US", locationDto.getCode());
        Assert.assertEquals(LocationType.country, locationDto.getType());

        verify(locationRepository).findByTypeAndCode(LocationType.country, "US");
    }
}
