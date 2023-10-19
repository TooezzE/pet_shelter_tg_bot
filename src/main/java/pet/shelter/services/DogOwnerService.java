package pet.shelter.services;

import org.springframework.stereotype.Service;
import pet.shelter.exceptions.CatOwnersNotFoundException;
import pet.shelter.exceptions.DogOwnersNotFoundException;
import pet.shelter.model.CatOwners;
import pet.shelter.model.DogOwners;
import pet.shelter.repository.DogOwnerRepository;

import java.util.Collection;

/**
 * Класс для владельцев собак
 **/
@Service
public class DogOwnerService {

    private final DogOwnerRepository dogOwnerRepository;

    public DogOwnerService(DogOwnerRepository dogOwnerRepository) {
        this.dogOwnerRepository = dogOwnerRepository;
    }
    public DogOwners findOwner(Long id) {
        return this.dogOwnerRepository.findById(id)
                .orElseThrow(() -> new DogOwnersNotFoundException("Person not found"));
    }
    public DogOwners createOwner(DogOwners dogOwners) {
        return this.dogOwnerRepository.save(dogOwners);
    }
    public DogOwners updateOwner(DogOwners dogOwners) {
        if (dogOwners.getId() != null && findOwner(dogOwners.getId()) != null) {
            return dogOwnerRepository.save(dogOwners);
        }
        throw new DogOwnersNotFoundException("Person not found");
    }
    public void deleteOwner(Long id) {
        this.dogOwnerRepository.deleteById(id);
    }
    public Collection<DogOwners> getAll() {
        return this.dogOwnerRepository.findAll();
    }
    public Collection<DogOwners> getByChatId(Long chatId) {
        return this.dogOwnerRepository.findDogOwnerByChatId(chatId);
    }

}
