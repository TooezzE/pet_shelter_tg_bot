package pet.shelter.services;

import org.springframework.stereotype.Service;
import pet.shelter.exceptions.DogAdoptersNotFoundException;
import pet.shelter.model.DogAdopter;
import pet.shelter.repository.DogAdopterRepository;

import java.util.Collection;

/**
 * Класс для владельцев собак
 **/
@Service
public class DogAdopterService {

    private final DogAdopterRepository dogAdopterRepository;

    public DogAdopterService(DogAdopterRepository dogAdopterRepository) {
        this.dogAdopterRepository = dogAdopterRepository;
    }
    public DogAdopter findAdopter(Long id) {
        return this.dogAdopterRepository.findById(id)
                .orElseThrow(() -> new DogAdoptersNotFoundException("Person not found"));
    }
    public DogAdopter createAdopter(DogAdopter dogAdopter) {
        return this.dogAdopterRepository.save(dogAdopter);
    }
    public DogAdopter updateAdopter(DogAdopter dogAdopter) {
        if (dogAdopter.getId() != null && findAdopter(dogAdopter.getId()) != null) {
            return dogAdopterRepository.save(dogAdopter);
        }
        throw new DogAdoptersNotFoundException("Person not found");
    }
    public void deleteAdopter(Long id) {
        this.dogAdopterRepository.deleteById(id);
    }
    public Collection<DogAdopter> getAll() {
        return this.dogAdopterRepository.findAll();
    }
    public Collection<DogAdopter> getByChatId(Long chatId) {
        return this.dogAdopterRepository.findDogAdopterByChatId(chatId);
    }

}
