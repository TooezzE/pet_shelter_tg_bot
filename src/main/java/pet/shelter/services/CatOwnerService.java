package pet.shelter.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger= LoggerFactory.getLogger(CatOwnerRepository.class);
    public CatOwners findOwner(Long id) {
        logger.info("Вызван метод поиска CatOwmers по id",id);
        return catOwnerRepository.findById(id)
                .orElseThrow(() -> new CatOwnersNotFoundException("Person not found"));
    }
    public CatOwners createOwner(CatOwners catOwners) {
        logger.info("Вызван метод создания CatOwmers");
                return catOwnerRepository.save(catOwners);
    }
    public CatOwners updateOwner(CatOwners catOwners) {
        logger.info("Вызван метод изменения CatOwmers");
               if (catOwners.getId() != null && findOwner(catOwners.getId()) != null) {
            return catOwnerRepository.save(catOwners);
        }
        throw new CatOwnersNotFoundException("Person not found");
    }
    public void deleteOwner(Long id) {
        logger.info("Вызван метод удаления CatOwmers по id",id);
              catOwnerRepository.deleteById(id);
    }
    public Collection<CatOwners> getAll() {
        logger.info("Вызван метод вывода всех CatOwmers");
               return catOwnerRepository.findAll();
    }
    public Collection<CatOwners> getByChatId(Long chatId) {
               return catOwnerRepository.findCatOwnerByChatId(chatId);
    }


}
