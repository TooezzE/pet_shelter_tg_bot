package pet.shelter.servise;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pet.shelter.model.Cat;

import pet.shelter.repository.CatRepository;

import pet.shelter.services.CatService;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class CatServiceTest {
    @Mock
    private CatRepository repository;
    @InjectMocks
    private CatService service;

    @Test
    public void testFindById() {
        Cat cat = new Cat(1L, "Кот", "Порода", 2010, "Описание");
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(cat));
        Cat result = service.findById(1l);
        Assertions.assertEquals(cat, result);
    }

    @Test
    public void testCreatecat() {

        Cat cat = new Cat(1L, "Кот", "Порода", 2010, "Описание");
        Mockito.when(repository.save(cat)).thenReturn(cat);
        Cat createdcat = service.create(cat);
        Mockito.verify(repository, Mockito.times(1)).save(cat);
        Assertions.assertEquals(cat.getName(), createdcat.getName());
        Assertions.assertEquals(cat.getId(), createdcat.getId());
    }

    @Test
    public void testUpdatecat() {
        Cat cat1 = new Cat();
        cat1.setName("Кот1");
        cat1.setDescriptionOfThePet("Описание1");
        Mockito.when(repository.save(cat1)).thenReturn(cat1);
        Cat updatedcat1 = service.create(cat1);
        Assertions.assertEquals(updatedcat1.getName(), "Кот1");
        Assertions.assertEquals(updatedcat1.getDescriptionOfThePet(), "Описание1");
    }

    @Test
    void testDelete() {
        long testId = 1;
        Cat cat = new Cat();
        cat.setId(testId);

        CatRepository catRepository = mock(CatRepository.class);
        doNothing().when(catRepository).deleteById(testId);
        CatService service = new CatService(catRepository);
        service.delete(testId);
        Mockito.verify(catRepository, Mockito.times(1)).deleteById(testId);
    }

    @Test
    void getAll() {
        List<Cat> catList = new ArrayList<>();
        Cat cat1 = new Cat();
        cat1.setId(1L);
        Cat cat2 = new Cat();
        cat2.setId(2L);
        Mockito.when(repository.findAll()).thenReturn(catList);
        CatService service = new CatService(repository);
        Collection<Cat> result = service.findAll();
        Assertions.assertEquals(catList, result);
        Mockito.verify(repository, Mockito.times(1)).findAll();
    }
}





