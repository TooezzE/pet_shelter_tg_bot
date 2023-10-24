package pet.shelter.servise;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pet.shelter.model.Dog;
import pet.shelter.model.DogAdopter;
import pet.shelter.repository.DogAdopterRepository;
import pet.shelter.services.DogAdopterService;
import pet.shelter.services.DogService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
    public void testUpdateDogOwners() {//не проходит
        DogAdopter dogAdopter1 = new DogAdopter();
        dogAdopter1.setName("Иван");
        DogAdopter dogAdopter2 = new DogAdopter();
        dogAdopter2.setName("Сережа");
        DogAdopter dogAdopter3 = new DogAdopter();
        dogAdopter3.setName("Олег");
        DogAdopter updatedDogOwner1 = service.createAdopter(dogAdopter1);
        DogAdopter updatedDogOwner2 = service.createAdopter(dogAdopter2);
        DogAdopter updatedDogOwner3 = service.createAdopter(dogAdopter3);
        Assertions.assertEquals(updatedDogOwner1.getName(), "Иван");
        Assertions.assertEquals(updatedDogOwner1.getId(), Long.valueOf(1L));
        Assertions.assertEquals(updatedDogOwner2.getName(), "Сережа");
        Assertions.assertEquals(updatedDogOwner2.getId(),Long.valueOf(2L));
        Assertions.assertEquals(updatedDogOwner3.getName(), "Олег");
        Assertions.assertEquals(updatedDogOwner3.getId(), Long.valueOf(3L));
    }
}






