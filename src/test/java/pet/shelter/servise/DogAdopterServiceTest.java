package pet.shelter.servise;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pet.shelter.model.Cat;
import pet.shelter.model.Dog;
import pet.shelter.model.DogAdopter;
import pet.shelter.model.Report;
import pet.shelter.repository.CatRepository;
import pet.shelter.repository.DogAdopterRepository;
import pet.shelter.services.CatService;
import pet.shelter.services.DogAdopterService;
import pet.shelter.services.DogService;
import pet.shelter.services.ReportService;

import java.util.*;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class DogAdopterServiceTest {
    @Mock
    private DogAdopterRepository repository;
    @InjectMocks
    private DogAdopterService service;
    @Test
    public void findById(){
        Long testId=1L;
        DogAdopter adopter = new DogAdopter();
        adopter.setName("Иван");
        Mockito.when(repository.findById(testId)).thenReturn(Optional.of(adopter));
        DogAdopter result = service.findAdopter(1l);
        Assertions.assertEquals(adopter, result);
    }
    @Test
    public void testGetByChatId() {
        Long testChatId = 5456L;
        DogAdopter adopter = new DogAdopter();
        adopter.setChatId(2222L);
        Set<DogAdopter> adopterSet = new HashSet<>();
        adopterSet.add(adopter);
        Mockito.when(repository.findDogAdopterByChatId(testChatId)).thenReturn(adopterSet);
        Set<DogAdopter> result = (Set<DogAdopter>) service.getByChatId(testChatId);
        Assertions.assertEquals(adopterSet, result);
    }
    @Test
    public void testUpdate() {
        DogAdopter dogAdopter1 = new DogAdopter();
        dogAdopter1.setName("Иван");
        Mockito.when(repository.save(dogAdopter1)).thenReturn(dogAdopter1);
        DogAdopter updatedDogAdopter1 = service.createAdopter(dogAdopter1);
        Assertions.assertEquals(updatedDogAdopter1.getName(), "Иван");

    }

    @Test
    void getAll() {
        List<DogAdopter> dogAdopters =  new ArrayList<>();
        DogAdopter adopter1 = new DogAdopter();
        adopter1.setName("Имя1");
        DogAdopter adopter2 = new DogAdopter();
        adopter2.setName("Имя2");
        Mockito.when(repository.findAll()).thenReturn(dogAdopters);
        DogAdopterService service = new DogAdopterService(repository);
        Collection<DogAdopter> result = service.getAll();
        Assertions.assertEquals(dogAdopters, result);
    }
    @Test
    void testDelete() {
        long testId=1;
        DogAdopter dogAdopter = new DogAdopter();
        dogAdopter.getId();

        DogAdopterRepository repository = mock(DogAdopterRepository.class);
        doNothing().when(repository).deleteById(testId);
        DogAdopterService service = new DogAdopterService(repository);
        service.deleteAdopter(testId);

}
    @Test
    public void testCreate() {

        DogAdopter adopter = new DogAdopter();
        Mockito.when(repository.save(adopter)).thenReturn(adopter);
        DogAdopter create = service.createAdopter(adopter);
        Mockito.verify(repository, Mockito.times(1)).save(adopter);
        Assertions.assertEquals(adopter.getName(), create.getName());
        Assertions.assertEquals(adopter.getId(), create.getId());
    }

}






