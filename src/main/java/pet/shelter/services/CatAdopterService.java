package pet.shelter.services;

import org.springframework.stereotype.Service;
import pet.shelter.exceptions.CatAdoptersNotFoundException;
import pet.shelter.model.CatAdopter;
import pet.shelter.repository.CatAdopterRepository;

import java.util.Collection;

/**
 * Класс Сервис для владельцев котов
 **/
@Service
public class CatAdopterService {

    private final CatAdopterRepository catAdopterRepository;

    public CatAdopterService(CatAdopterRepository catAdopterRepository) {
        this.catAdopterRepository = catAdopterRepository;
    }
    public CatAdopter findAdopter(Long id) {
        return this.catAdopterRepository.findById(id)
                .orElseThrow(() -> new CatAdoptersNotFoundException("Person not found"));
    }
    public CatAdopter createAdopter(CatAdopter catAdopter) {
                return this.catAdopterRepository.save(catAdopter);
    }
    public CatAdopter updateAdopter(CatAdopter catAdopter) {
               if (catAdopter.getId() != null && findAdopter(catAdopter.getId()) != null) {
            return catAdopterRepository.save(catAdopter);
        }
        throw new CatAdoptersNotFoundException("Person not found");
    }
    public void deleteAdopter(Long id) {
               this.catAdopterRepository.deleteById(id);
    }
    public Collection<CatAdopter> getAll() {
               return this.catAdopterRepository.findAll();
    }
    public Collection<CatAdopter> getByChatId(Long chatId) {
               return this.catAdopterRepository.findCatAdopterByChatId(chatId);
    }


}
