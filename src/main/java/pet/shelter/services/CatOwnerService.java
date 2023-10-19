package pet.shelter.services;

import org.springframework.stereotype.Service;
import pet.shelter.exceptions.CatOwnersNotFoundException;
import pet.shelter.model.CatOwners;
import pet.shelter.repository.CatOwnerRepository;

import java.util.Collection;

/**
 * Класс Сервис для владельцев котов
 **/
@Service
public class CatOwnerService {

    private final CatOwnerRepository catOwnerRepository;

    public CatOwnerService(CatOwnerRepository catOwnerRepository) {
        this.catOwnerRepository = catOwnerRepository;
    }
    public CatOwners findOwner(Long id) {
        return this.catOwnerRepository.findById(id)
                .orElseThrow(() -> new CatOwnersNotFoundException("Person not found"));
    }
    public CatOwners createOwner(CatOwners catOwners) {
                return this.catOwnerRepository.save(catOwners);
    }
    public CatOwners updateOwner(CatOwners catOwners) {
               if (catOwners.getId() != null && findOwner(catOwners.getId()) != null) {
            return catOwnerRepository.save(catOwners);
        }
        throw new CatOwnersNotFoundException("Person not found");
    }
    public void deleteOwner(Long id) {
               this.catOwnerRepository.deleteById(id);
    }
    public Collection<CatOwners> getAll() {
               return this.catOwnerRepository.findAll();
    }
    public Collection<CatOwners> getByChatId(Long chatId) {
               return this.catOwnerRepository.findCatOwnerByChatId(chatId);
    }


}
