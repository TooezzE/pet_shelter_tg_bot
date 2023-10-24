package pet.shelter.servise;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pet.shelter.exceptions.CatNotFoundException;
import pet.shelter.exceptions.DogNotFoundException;
import pet.shelter.model.Dog;
import pet.shelter.repository.DogRepository;
import pet.shelter.services.DogService;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class DogServiceTest {
    @Mock
    private DogRepository repository;
    @InjectMocks
    private DogService service;

    @Test
    public void testFindById() {
        Dog dog = new Dog(1L, "Собака", "Порода", 2010, "Описание");
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(dog));
        Dog result = service.findById(1l);
        Assertions.assertEquals(dog, result);
    }

    @Test
    public void testCreateDog() {

        Dog dog = new Dog(1L, "Собака", "Порода", 2010, "Описание");
        Mockito.when(repository.save(dog)).thenReturn(dog);
        Dog createdDog = service.create(dog);
        Mockito.verify(repository, Mockito.times(1)).save(dog);
        Assertions.assertEquals(dog.getName(), createdDog.getName());
        Assertions.assertEquals(dog.getId(), createdDog.getId());
    }

    @Test
    public void testUpdateDog() {//не работает
        Dog dog1 = new Dog();
        dog1.setName("Собака1");
        dog1.setDescriptionOfThePet("Описание1");
        Dog dog2 = new Dog();
        dog2.setName("Собака2");
        dog2.setDescriptionOfThePet("Описание2");
        Dog dog3 = new Dog();
        dog3.setName("Собака3");
        dog3.setDescriptionOfThePet("Описание3");
        Mockito.when(repository.save(dog1)).thenReturn(dog1);
        Dog updatedDog1 = service.update(1L, dog1);
        Dog updatedDog2 = service.update(2L, dog2);
        Dog updatedDog3 = service.update(3L, dog3);
        Assertions.assertEquals(updatedDog1.getName(), "Собака1");
        Assertions.assertEquals(updatedDog1.getDescriptionOfThePet(), "Описание1");
        Assertions.assertEquals(updatedDog1.getId(), Long.valueOf(1L));
        Assertions.assertEquals(updatedDog2.getName(), "Собака2");
        Assertions.assertEquals(updatedDog2.getDescriptionOfThePet(), "Описание2");
        Assertions.assertEquals(updatedDog2.getId(), Long.valueOf(2L));
        Assertions.assertEquals(updatedDog3.getName(), "Собака3");
        Assertions.assertEquals(updatedDog3.getDescriptionOfThePet(), "Описание3");
        Assertions.assertEquals(updatedDog3.getId(), Long.valueOf(3L));
        Dog dog4 = new Dog();
        Assertions.assertThrows(DogNotFoundException.class, () -> {
            service.update(4L, dog4);
        });
    }

    }



