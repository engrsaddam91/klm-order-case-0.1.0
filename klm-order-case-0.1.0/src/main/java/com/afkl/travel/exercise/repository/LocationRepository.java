package com.afkl.travel.exercise.repository;

import com.afkl.travel.exercise.entity.Location;
import com.afkl.travel.exercise.entity.LocationType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository<Location, Integer> {
    Location findByTypeAndCode(LocationType type, String code);
}
