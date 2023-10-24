package pet.shelter.services;

import org.springframework.stereotype.Service;
import pet.shelter.exceptions.DogAdoptersNotFoundException;
import pet.shelter.model.DogAdopter;
import pet.shelter.repository.DogAdopterRepository;

import java.util.Collection;

/**
 * Dogs adopters service
 **/
@Service
public class DogAdopterService {

    private final DogAdopterRepository repository;

    public DogAdopterService(DogAdopterRepository repository) {
        this.repository = repository;
    }
    public DogAdopter findAdopter(Long id) {
        return repository.findById(id)
                .orElseThrow(DogAdoptersNotFoundException::new);
    }
    public DogAdopter createAdopter(DogAdopter dogAdopter) {
        return repository.save(dogAdopter);
    }
    public DogAdopter updateAdopter(Long id, DogAdopter dogAdopter) {
        DogAdopter foundedAdopter = repository.findById(id).orElseThrow(DogAdoptersNotFoundException::new);
        foundedAdopter.setDog(dogAdopter.getDog());
        foundedAdopter.setChatId(dogAdopter.getChatId());
        foundedAdopter.setName(dogAdopter.getName());
        foundedAdopter.setAddress(dogAdopter.getAddress());
        foundedAdopter.setPhoneNumber(dogAdopter.getPhoneNumber());
        foundedAdopter.setEmail(dogAdopter.getEmail());

        return repository.save(foundedAdopter);
    }
    public void deleteAdopter(Long id) {
        repository.deleteById(id);
    }
    public Collection<DogAdopter> getAll() {
        return repository.findAll();
    }
    public Collection<DogAdopter> getByChatId(Long chatId) {
        return repository.findDogAdopterByChatId(chatId);
    }

}
