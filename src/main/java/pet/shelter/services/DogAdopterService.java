package pet.shelter.services;

import org.springframework.stereotype.Service;
import pet.shelter.exceptions.DogOwnersNotFoundException;
import pet.shelter.model.DogAdopter;
import pet.shelter.repository.DogOwnerRepository;

import java.util.Collection;

/**
 * Класс для владельцев собак
 **/
@Service
public class DogAdopterService {

    private final DogOwnerRepository dogOwnerRepository;

    public DogAdopterService(DogOwnerRepository dogOwnerRepository) {
        this.dogOwnerRepository = dogOwnerRepository;
    }
    public DogAdopter findOwner(Long id) {
        return this.dogOwnerRepository.findById(id)
                .orElseThrow(() -> new DogOwnersNotFoundException("Person not found"));
    }
    public DogAdopter createOwner(DogAdopter dogAdopter) {
        return this.dogOwnerRepository.save(dogAdopter);
    }
    public DogAdopter updateOwner(DogAdopter dogAdopter) {
        if (dogAdopter.getId() != null && findOwner(dogAdopter.getId()) != null) {
            return dogOwnerRepository.save(dogAdopter);
        }
        throw new DogOwnersNotFoundException("Person not found");
    }
    public void deleteOwner(Long id) {
        this.dogOwnerRepository.deleteById(id);
    }
    public Collection<DogAdopter> getAll() {
        return this.dogOwnerRepository.findAll();
    }
    public Collection<DogAdopter> getByChatId(Long chatId) {
        return this.dogOwnerRepository.findDogOwnerByChatId(chatId);
    }

}
