package com.petconnect.petplatform.service;

import com.petconnect.petplatform.dto.AdoptionRequestDto;
import com.petconnect.petplatform.model.AdoptionRequest;
import com.petconnect.petplatform.model.Animal;
import com.petconnect.petplatform.model.User;
import com.petconnect.petplatform.repository.AdoptionRequestRepository;
import com.petconnect.petplatform.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdoptionService {

    private final AdoptionRequestRepository adoptionRequestRepository;
    private final AnimalRepository animalRepository;

    public AdoptionRequest createAdoptionRequest(Long animalId, AdoptionRequestDto dto) {
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new RuntimeException("Animal not found"));

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        AdoptionRequest request = AdoptionRequest.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .phoneNumber(dto.getPhoneNumber())
                .animal(animal)
                .user(currentUser)
                .build();

        return adoptionRequestRepository.save(request);
    }
}
