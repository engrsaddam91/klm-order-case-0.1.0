package com.afkl.travel.exercise.util;

import com.afkl.travel.exercise.dto.LocationDto;
import com.afkl.travel.exercise.entity.Location;
import com.afkl.travel.exercise.entity.Translation;
import com.afkl.travel.exercise.exception.LocationNotFoundException;
import com.afkl.travel.exercise.exception.TranslationNotFoundException;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class TravelHelper {

    public static Set<LocationDto> convertTranslationsToLocationDto(Set<Translation> translations) {
        Set<LocationDto> locations = new HashSet<>();
        if (!translations.isEmpty()) {
            translations.forEach(tr -> {
                LocationDto locationDto = LocationDto.builder()
                        .code(tr.getLocation().getCode())
                        .name(tr.getName())
                        .type(tr.getLocation().getType())
                        .latitude(tr.getLocation().getLatitude())
                        .longitude(tr.getLocation().getLongitude())
                        .description(tr.getDescription())
                        .build();
                if (Objects.nonNull(tr.getLocation().getParent())) {
                    locationDto.setParentCode(tr.getLocation().getParent().getCode());
                    locationDto.setParentType(tr.getLocation().getParent().getType());
                }
                locations.add(locationDto);
            });
        }
        return locations;
    }

    public static LocationDto convertToLocationDto(final Location location, final String language) {
        LocationDto locationDto = LocationDto.builder()
                .code(location.getCode())
                .type(location.getType())
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .build();
        if (Objects.nonNull(location.getParent())) {
            locationDto.setParentCode(location.getParent().getCode());
            locationDto.setParentType(location.getParent().getType());
        }
        Optional<Translation> translation = location.getTranslations()
                .stream()
                .filter(tr -> tr.getLanguage().equals(language))
                .findFirst();
        if (translation.isPresent()) {
            locationDto.setName(translation.get().getName());
            locationDto.setDescription(translation.get().getDescription());
        } else {
            throw new TranslationNotFoundException(String.format("Translation not found for language %s, type %s, code %s", language, locationDto.getType(), locationDto.getCode()));
        }
        return locationDto;
    }
}
