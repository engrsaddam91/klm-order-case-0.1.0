package com.afkl.travel.exercise.repository;

import com.afkl.travel.exercise.entity.Translation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TranslationRepository extends CrudRepository<Translation, Integer> {
    Set<Translation> findAllByLanguage(String language);
}
