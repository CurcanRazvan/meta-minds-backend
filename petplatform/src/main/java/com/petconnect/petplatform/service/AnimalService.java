package com.petconnect.petplatform.service;

import com.petconnect.petplatform.model.Animal;
import com.petconnect.petplatform.model.User;
import com.petconnect.petplatform.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    public Page<Animal> getAllAnimals(Pageable pageable) {
        return animalRepository.findAll(pageable);
    }

    public Optional<Animal> getAnimalById(Long id) {
        return animalRepository.findById(id);
    }

    public Animal addAnimal(Animal animal) {
        animal.setAdopted(false); // by default, new animal is not adopted
        animal.setAdoptedBy(null);
        return animalRepository.save(animal);
    }

    public Animal updateAnimal(Long id, Animal animalDetails) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal not found"));
        animal.setName(animalDetails.getName());
        animal.setBreed(animalDetails.getBreed());
        animal.setAge(animalDetails.getAge());
        animal.setLocation(animalDetails.getLocation());
        return animalRepository.save(animal);
    }

    public void deleteAnimal(Long id) {
        animalRepository.deleteById(id);
    }

    public Animal adoptAnimal(Long id) {
        Optional<Animal> optionalAnimal = animalRepository.findById(id);
        if (optionalAnimal.isEmpty()) {
            return null;
        }

        Animal animal = optionalAnimal.get();

        if (animal.isAdopted()) {
            throw new RuntimeException("Animalul este deja adoptat");
        }

        // Obținem utilizatorul autentificat
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        animal.setAdopted(true);
        animal.setAdoptedBy(currentUser);

        return animalRepository.save(animal);
    }

    public List<Animal> filterAnimals(String breed, String location, Integer age) {
        if (age != null) {
            return animalRepository.findByBreedContainingIgnoreCaseAndLocationContainingIgnoreCaseAndAge(
                    breed != null ? breed : "",
                    location != null ? location : "",
                    age
            );
        } else {
            return animalRepository.findByBreedContainingIgnoreCaseAndLocationContainingIgnoreCase(
                    breed != null ? breed : "",
                    location != null ? location : ""
            );
        }
    }

    public Page<Animal> filterAnimalsWithPagination(String breed, String location, Pageable pageable) {
        return animalRepository.findByBreedContainingIgnoreCaseAndLocationContainingIgnoreCase(
                breed != null ? breed : "",
                location != null ? location : "",
                pageable
        );
    }
}
