package com.afkl.travel.exercise.controller;

import com.afkl.travel.exercise.dto.LocationDto;
import com.afkl.travel.exercise.entity.LocationType;
import com.afkl.travel.exercise.service.TravelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/travel")
@Slf4j
public class TravelController {

    private static final String LOCATIONS_BY_LANGUAGE_ENDPOINT = "/locations";
    private static final String LOCATIONS_BY_TYPECODE_ENDPOINT = "/locations/{type}/{code}";

    private TravelService travelService;

    public TravelController(final TravelService travelService) {
        this.travelService = travelService;
    }

    @GetMapping(value = LOCATIONS_BY_LANGUAGE_ENDPOINT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<LocationDto> fetchLocationsByLanguage(@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, defaultValue = "EN") String language) {
        log.info("Start Fetching Locations By Language {}", language);
        final Set<LocationDto> locationDtos = travelService.getLocations(language);
        log.info("{} Locations are fetched for Language {}", locationDtos.size(), language);
        return locationDtos;
    }

    @GetMapping(value = LOCATIONS_BY_TYPECODE_ENDPOINT, produces = MediaType.APPLICATION_JSON_VALUE)
    public LocationDto fetchLocationsByTypeAndCode(@PathVariable("type") LocationType type, @PathVariable("code") String code, @RequestHeader(name = "accept-language", defaultValue = "EN") String language) {
        log.info("Start Fetching Location By Type {}, Code {}, Language {}", type, code, language);
        final LocationDto locationDto = travelService.getLocation(type, code, language);
        log.info("End Fetching Location By Type {}, Code {}, Language {}", type, code, language);
        return locationDto;
    }
}
