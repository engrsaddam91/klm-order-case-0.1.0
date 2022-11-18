package com.afkl.travel.exercise.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    @Id
    private Integer id;
    private String code;
    @Enumerated(EnumType.STRING)
    private LocationType type;
    private Double longitude;
    private Double latitude;
    @OneToOne
    private Location parent;
    @OneToMany(mappedBy = "location")
    private Set<Translation> translations;
}
