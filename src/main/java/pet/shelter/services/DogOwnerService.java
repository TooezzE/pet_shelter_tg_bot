package pet.shelter.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pet.shelter.exceptions.CatOwnersNotFoundException;
import pet.shelter.exceptions.DogOwnersNotFoundException;
import pet.shelter.model.CatOwners;
import pet.shelter.model.DogOwners;
import pet.shelter.repository.CatOwnerRepository;
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

    private static final Logger logger= LoggerFactory.getLogger(DogOwnerRepository.class);
    public DogOwners findOwner(Long id) {
        logger.info("Вызван метод поиска DogOwmers по id",id);
        return dogOwnerRepository.findById(id)
                .orElseThrow(() -> new DogOwnersNotFoundException("Person not found"));
    }
    public DogOwners createOwner(DogOwners dogOwners) {
        logger.info("Вызван метод создания DogOwmers");
        return dogOwnerRepository.save(dogOwners);
    }
    public DogOwners updateOwner(DogOwners dogOwners) {
        logger.info("Вызван метод изменеия DogOwmers");
        if (dogOwners.getId() != null && findOwner(dogOwners.getId()) != null) {
            return dogOwnerRepository.save(dogOwners);
        }
        throw new DogOwnersNotFoundException("Person not found");
    }
    public void deleteOwner(Long id) {
        logger.info("Вызван метод удаления dogOwmers по id",id);
        dogOwnerRepository.deleteById(id);
    }
    public Collection<DogOwners> getAll() {
        logger.info("Вызван метод вывода всех DogOwmers");
        return dogOwnerRepository.findAll();
    }
    public Collection<DogOwners> getByChatId(Long chatId) {
        return dogOwnerRepository.findDogOwnerByChatId(chatId);
    }

}
