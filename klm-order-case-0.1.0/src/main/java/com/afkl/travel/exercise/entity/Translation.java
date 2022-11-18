package com.afkl.travel.exercise.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Translation {

    @Id
    private Integer id;
    @Column(name = "language")
    private String language;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @OneToOne
    @JoinColumn(name = "location", referencedColumnName = "id")
    private Location location;
}
