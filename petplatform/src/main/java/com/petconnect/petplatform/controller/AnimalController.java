package com.petconnect.petplatform.controller;

import com.petconnect.petplatform.dto.AdoptionRequestDto;
import com.petconnect.petplatform.model.Animal;
import com.petconnect.petplatform.service.AdoptionService;
import com.petconnect.petplatform.service.AnimalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animals")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @Autowired
    private AdoptionService adoptionService;

    @GetMapping
    public ResponseEntity<List<Animal>> getAllAnimals() {
        return ResponseEntity.ok(animalService.getAllAnimals());
    }

    @PostMapping
    public ResponseEntity<Animal> addAnimal(@RequestBody Animal animal) {
        return ResponseEntity.ok(animalService.addAnimal(animal));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Animal> updateAnimal(@PathVariable Long id, @RequestBody Animal animalDetails) {
        return ResponseEntity.ok(animalService.updateAnimal(id, animalDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAnimal(@PathVariable Long id) {
        animalService.deleteAnimal(id);
        return ResponseEntity.ok("Animal deleted successfully.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> getAnimalById(@PathVariable Long id) {
        return animalService.getAnimalById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/adopt")
    public ResponseEntity<String> adoptAnimal(@PathVariable Long id) {
        Animal adoptedAnimal = animalService.adoptAnimal(id);
        if (adoptedAnimal != null) {
            return ResponseEntity.ok("Animal adopted successfully.");
        } else {
            return ResponseEntity.badRequest().body("Animal not found.");
        }
    }

    @PostMapping("/{id}/adoption-request")
    public ResponseEntity<String> createAdoptionRequest(
            @PathVariable Long id,
            @Valid @RequestBody AdoptionRequestDto dto) {
        adoptionService.createAdoptionRequest(id, dto);
        return ResponseEntity.ok("Adoption request submitted successfully.");
    }


    @GetMapping("/filter")
    public ResponseEntity<List<Animal>> filterAnimals(
            @RequestParam(required = false) String breed,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Integer age
    ) {
        return ResponseEntity.ok(animalService.filterAnimals(breed, location, age));
    }

    @GetMapping("/filter-paged")
    public ResponseEntity<Page<Animal>> filterAnimalsWithPagination(
            @RequestParam(required = false) String breed,
            @RequestParam(required = false) String location,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(animalService.filterAnimalsWithPagination(breed, location, pageable));
    }
}
