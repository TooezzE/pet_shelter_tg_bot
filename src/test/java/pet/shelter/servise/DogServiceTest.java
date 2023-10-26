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
import pet.shelter.model.Report;
import pet.shelter.repository.DogRepository;
import pet.shelter.repository.ReportRepository;
import pet.shelter.services.DogService;
import pet.shelter.services.ReportService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

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
    public void testUpdateDog() {
        Dog dog1 = new Dog();
        dog1.setName("Собака1");
        dog1.setDescriptionOfThePet("Описание1");
        Mockito.when(repository.save(dog1)).thenReturn(dog1);
        Dog updatedDog1 = service.create(dog1);
        Assertions.assertEquals(updatedDog1.getName(), "Собака1");
        Assertions.assertEquals(updatedDog1.getDescriptionOfThePet(), "Описание1");
    }

    @Test
    void testDelete() {
        long testId = 1;
        Dog dog = new Dog();
        dog.setId(testId);

        DogRepository dogRepository = mock(DogRepository.class);
        doNothing().when(dogRepository).deleteById(testId);
        DogService service = new DogService(dogRepository);
        service.delete(testId);
        Mockito.verify(dogRepository, Mockito.times(1)).deleteById(testId);
    }

    @Test
    void getAll() {
        List<Dog> dogList = new ArrayList<>();
        Dog dog1 = new Dog();
        dog1.setId(1L);
        Dog dog2 = new Dog();
        dog2.setId(2L);
        Mockito.when(repository.findAll()).thenReturn(dogList);
        DogService service = new DogService(repository);
        Collection<Dog> result = service.findAll();
        Assertions.assertEquals(dogList, result);
        Mockito.verify(repository, Mockito.times(1)).findAll();
    }
}





