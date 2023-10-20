package pet.shelter.services;

import org.springframework.stereotype.Service;
import pet.shelter.exceptions.CatOwnersNotFoundException;
import pet.shelter.model.CatAdopter;
import pet.shelter.repository.CatOwnerRepository;

import java.util.Collection;

/**
 * Класс Сервис для владельцев котов
 **/
@Service
public class CatAdopterService {

    private final CatOwnerRepository catOwnerRepository;

    public CatAdopterService(CatOwnerRepository catOwnerRepository) {
        this.catOwnerRepository = catOwnerRepository;
    }
    public CatAdopter findOwner(Long id) {
        return this.catOwnerRepository.findById(id)
                .orElseThrow(() -> new CatOwnersNotFoundException("Person not found"));
    }
    public CatAdopter createOwner(CatAdopter catAdopter) {
                return this.catOwnerRepository.save(catAdopter);
    }
    public CatAdopter updateOwner(CatAdopter catAdopter) {
               if (catAdopter.getId() != null && findOwner(catAdopter.getId()) != null) {
            return catOwnerRepository.save(catAdopter);
        }
        throw new CatOwnersNotFoundException("Person not found");
    }
    public void deleteOwner(Long id) {
               this.catOwnerRepository.deleteById(id);
    }
    public Collection<CatAdopter> getAll() {
               return this.catOwnerRepository.findAll();
    }
    public Collection<CatAdopter> getByChatId(Long chatId) {
               return this.catOwnerRepository.findCatOwnerByChatId(chatId);
    }


}
