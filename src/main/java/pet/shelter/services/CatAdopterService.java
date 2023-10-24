package pet.shelter.services;

import org.springframework.stereotype.Service;
import pet.shelter.exceptions.CatAdoptersNotFoundException;
import pet.shelter.model.CatAdopter;
import pet.shelter.repository.CatAdopterRepository;

import java.util.Collection;

/**
 * Cats adopters service
 **/
@Service
public class CatAdopterService {

    private final CatAdopterRepository repository;

    public CatAdopterService(CatAdopterRepository repository) {
        this.repository = repository;
    }
    public CatAdopter findAdopter(Long id) {
        return this.repository.findById(id)
                .orElseThrow(CatAdoptersNotFoundException::new);
    }
    public CatAdopter createAdopter(CatAdopter catAdopter) {
                return this.repository.save(catAdopter);
    }
    public CatAdopter updateAdopter(Long id, CatAdopter catAdopter) {
        CatAdopter foundedAdopter = repository.findById(id).orElseThrow(CatAdoptersNotFoundException::new);
        foundedAdopter.setCat(catAdopter.getCat());
        foundedAdopter.setAddress(catAdopter.getAddress());
        foundedAdopter.setChatId(catAdopter.getChatId());
        foundedAdopter.setName(catAdopter.getName());
        foundedAdopter.setEmail(catAdopter.getEmail());
        foundedAdopter.setPhoneNumber(catAdopter.getPhoneNumber());

        return repository.save(foundedAdopter);
    }
    public void deleteAdopter(Long id) {
               this.repository.deleteById(id);
    }
    public Collection<CatAdopter> getAll() {
        return repository.findAll();
    }
    public Collection<CatAdopter> getByChatId(Long chatId) {
        return repository.findCatAdopterByChatId(chatId);
    }


}
