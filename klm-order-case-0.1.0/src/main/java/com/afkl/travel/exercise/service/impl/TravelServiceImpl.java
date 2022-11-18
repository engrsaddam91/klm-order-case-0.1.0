package com.afkl.travel.exercise.service.impl;

import com.afkl.travel.exercise.dto.LocationDto;
import com.afkl.travel.exercise.entity.Location;
import com.afkl.travel.exercise.entity.LocationType;
import com.afkl.travel.exercise.entity.Translation;
import com.afkl.travel.exercise.exception.LocationNotFoundException;
import com.afkl.travel.exercise.exception.TranslationNotFoundException;
import com.afkl.travel.exercise.repository.LocationRepository;
import com.afkl.travel.exercise.repository.TranslationRepository;
import com.afkl.travel.exercise.service.TravelService;
import com.afkl.travel.exercise.util.TravelHelper;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Set;

@Service
@Slf4j
public class TravelServiceImpl implements TravelService {

    private TranslationRepository translationRepository;
    private LocationRepository locationRepository;

    public TravelServiceImpl(final TranslationRepository translationRepository, final LocationRepository locationRepository) {
        this.translationRepository = translationRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Set<LocationDto> getLocations(final String language) {
        log.info("Getting Locations for language {}", language);
        final Set<Translation> translations = translationRepository.findAllByLanguage(language);
        log.info("Done Getting Locations for language {}", language);
        return TravelHelper.convertTranslationsToLocationDto(translations);
    }

    @Override
    @Transactional(readOnly = true)
    public LocationDto getLocation(final LocationType type, final String code, final String language) throws TranslationNotFoundException, LocationNotFoundException {
        log.info("Getting Location for type {}, code {} ,language {}", type, code, language);
        final Location location = locationRepository.findByTypeAndCode(type, code);
        if(Objects.isNull(location)) {
            throw new LocationNotFoundException(String.format("Location not found for language %s", language));
        }
        log.info("Done Getting Location for type {}, code {} ,language {}", type, code, language);
        return TravelHelper.convertToLocationDto(location, language);
    }
}
