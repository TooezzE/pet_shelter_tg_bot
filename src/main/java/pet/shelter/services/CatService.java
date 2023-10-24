package pet.shelter.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pet.shelter.exceptions.CatNotFoundException;
import pet.shelter.model.Cat;
import pet.shelter.repository.CatRepository;

import java.util.Collection;

@Service
public class CatService {

    private final CatRepository repository;

    public CatService(CatRepository repository) {
        this.repository = repository;
    }
    private static final Logger logger= LoggerFactory.getLogger(CatRepository.class);

    public Cat create(Cat cat){
        logger.info("Вызван метод создания " + cat.getName());
        return repository.save(cat);
    }

    public  Cat findById(Long id){
        logger.info("Вызван метод поиска Cat по id = " + id);
        return repository.findById(id).orElseThrow(CatNotFoundException::new);
    }

    public Cat update(Long id, Cat cat){
        logger.info("Вызван метод изменения Cat, id = " + id);
        Cat foundedCat = repository.findById(id).orElseThrow(CatNotFoundException::new);
        foundedCat.setName(cat.getName());
        foundedCat.setDescriptionOfThePet(cat.getDescriptionOfThePet());
        return repository.save(foundedCat);
    }
    public void delete(Long id){
        logger.info("Вызван метод удаления Cat по id = " + id);
        repository.deleteById(id);
    }
    public Collection<Cat> findAll(){
        logger.info("Вызван метод вывода всех Cat");
        return repository.findAll();
    }
}
