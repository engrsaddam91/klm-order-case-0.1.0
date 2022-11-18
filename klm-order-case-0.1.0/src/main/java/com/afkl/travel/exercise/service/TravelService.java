package com.afkl.travel.exercise.service;


import com.afkl.travel.exercise.dto.LocationDto;
import com.afkl.travel.exercise.entity.LocationType;
import com.afkl.travel.exercise.exception.LocationNotFoundException;
import com.afkl.travel.exercise.exception.TranslationNotFoundException;
import javassist.NotFoundException;

import java.util.Set;

public interface TravelService {
    /**
     * Method to get locations for provided language
     *
     * @param language
     * @return Set<LocationDto>
     */
    public Set<LocationDto> getLocations(String language);

    /**
     * Method to get Location for provided type,code and language
     *
     * @param type
     * @param code
     * @param language
     * @return LocationDto
     */
    public LocationDto getLocation(LocationType type, String code, String language) throws TranslationNotFoundException, LocationNotFoundException;
}
