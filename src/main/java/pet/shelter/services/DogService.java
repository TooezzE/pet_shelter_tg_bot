package pet.shelter.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pet.shelter.exceptions.DogNotFoundException;
import pet.shelter.model.Cat;
import pet.shelter.model.Dog;
import pet.shelter.repository.CatRepository;
import pet.shelter.repository.DogRepository;

import java.util.Collection;

@Service
public class DogService {

    private final DogRepository repository;

    public DogService(DogRepository repository) {
        this.repository = repository;
    }
    private static final Logger logger= LoggerFactory.getLogger(DogRepository.class);
    public Dog create(Dog dog){
        logger.info("Вызван метод создания Dog");
        return repository.save(dog);
    }

    public  Dog findById(Long id) {
        logger.info("Вызван метод поиска Dog по id", id);
        return repository.findById(id).orElseThrow(DogNotFoundException::new);
    }

    public Dog update(Dog dog){
        logger.info("Вызван метод изменения Dog");
        return repository.save(dog);
    }
    public void delete(Long id){
        logger.info("Вызван метод удаления Dog по id",id);
        repository.deleteById(id);
    }
    public Collection<Dog> findAll(){
        logger.info("Вызван метод вывода всех Dog");
        return repository.findAll();
    }
}

