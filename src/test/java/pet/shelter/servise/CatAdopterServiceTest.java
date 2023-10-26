package pet.shelter.servise;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pet.shelter.model.CatAdopter;
import pet.shelter.repository.CatAdopterRepository;
import pet.shelter.services.CatAdopterService;

import java.util.*;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class CatAdopterServiceTest {
    @Mock
    private CatAdopterRepository repository;
    @InjectMocks
    private CatAdopterService service;
    @Test
    public void findById(){
        Long testId=1L;
        CatAdopter adopter = new CatAdopter();
        adopter.setName("Иван");
        Mockito.when(repository.findById(testId)).thenReturn(Optional.of(adopter));
        CatAdopter result = service.findAdopter(1l);
        Assertions.assertEquals(adopter, result);
    }
    @Test
    public void testGetByChatId() {
        Long testChatId = 5456L;
        CatAdopter adopter = new CatAdopter();
        adopter.setChatId(2222L);
        Set<CatAdopter> adopterSet = new HashSet<>();
        adopterSet.add(adopter);
        Mockito.when(repository.findCatAdopterByChatId(testChatId)).thenReturn(adopterSet);
        Set<CatAdopter> result = (Set<CatAdopter>) service.getByChatId(testChatId);
        Assertions.assertEquals(adopterSet, result);
    }
    @Test
    public void testUpdate() {
        CatAdopter CatAdopter1 = new CatAdopter();
        CatAdopter1.setName("Иван");
        Mockito.when(repository.save(CatAdopter1)).thenReturn(CatAdopter1);
        CatAdopter updatedCatAdopter1 = service.createAdopter(CatAdopter1);
        Assertions.assertEquals(updatedCatAdopter1.getName(), "Иван");

    }

    @Test
    void getAll() {
        List<CatAdopter> CatAdopters =  new ArrayList<>();
        CatAdopter adopter1 = new CatAdopter();
        adopter1.setName("Имя1");
        CatAdopter adopter2 = new CatAdopter();
        adopter2.setName("Имя2");
        Mockito.when(repository.findAll()).thenReturn(CatAdopters);
        CatAdopterService service = new CatAdopterService(repository);
        Collection<CatAdopter> result = service.getAll();
        Assertions.assertEquals(CatAdopters, result);
    }
    @Test
    void testDelete() {
        long testId=1;
        CatAdopter CatAdopter = new CatAdopter();
        CatAdopter.getId();

        CatAdopterRepository repository = mock(CatAdopterRepository.class);
        doNothing().when(repository).deleteById(testId);
        CatAdopterService service = new CatAdopterService(repository);
        service.deleteAdopter(testId);

}
    @Test
    public void testCreate() {

        CatAdopter adopter = new CatAdopter();
        Mockito.when(repository.save(adopter)).thenReturn(adopter);
        CatAdopter create = service.createAdopter(adopter);
        Mockito.verify(repository, Mockito.times(1)).save(adopter);
        Assertions.assertEquals(adopter.getName(), create.getName());
        Assertions.assertEquals(adopter.getId(), create.getId());
    }

}






