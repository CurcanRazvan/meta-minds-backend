package com.petconnect.petplatform.repository;

import com.petconnect.petplatform.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findByBreedContainingIgnoreCaseAndLocationContainingIgnoreCaseAndAge(
            String breed, String location, int age
    );
    List<Animal> findByBreedContainingIgnoreCaseAndLocationContainingIgnoreCase(
            String breed, String location
    );

    // Pentru filtrare cu paginare
    Page<Animal> findByBreedContainingIgnoreCaseAndLocationContainingIgnoreCase(
            String breed, String location, Pageable pageable
    );
}
