package com.afkl.travel.exercise.dto;

import com.afkl.travel.exercise.entity.LocationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocationDto {
    private String code;
    private String name;
    private LocationType type;
    private Double latitude;
    private Double longitude;
    private String description;
    private String parentCode;
    private LocationType parentType;
}
