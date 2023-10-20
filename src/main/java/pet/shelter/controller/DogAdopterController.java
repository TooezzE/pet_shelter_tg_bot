package pet.shelter.controller;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pet.shelter.model.DogAdopter;
import pet.shelter.services.DogAdopterService;

import java.util.Collection;

@RestController
@RequestMapping("/adopter-dog")
public class DogAdopterController {
    private final DogAdopterService service;

    public DogAdopterController(DogAdopterService service) {
        this.service = service;
    }

    @Operation(summary = "Создание пользователя")
    @PostMapping()
    public DogAdopter create(@RequestBody DogAdopter dogAdopter) {
        return service.createAdopter(dogAdopter);
    }

    @Operation(summary = "Получение пользователя по id")
    @GetMapping("/{id}")
    public ResponseEntity<DogAdopter> findAdopter(@PathVariable Long id) {
        DogAdopter owners = service.findAdopter(id);
        if (owners == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.findAdopter(id));
    }

    @Operation(summary = "Обновление данных пользователя")
    @PutMapping
    public ResponseEntity<DogAdopter> update(@RequestBody DogAdopter dogAdopter) {
        DogAdopter foundAdopter = service.createAdopter(dogAdopter);
        if (foundAdopter == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(service.updateAdopter(dogAdopter));
    }

    @Operation(summary = "Удаление пользователей по id")
    @DeleteMapping("/{id}")
    public ResponseEntity<DogAdopter> delete(@PathVariable Long id) {
        service.deleteAdopter(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Получение всех пользователей")
    @GetMapping("/all")
    public ResponseEntity<Collection<DogAdopter>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Получение всех пользователей")
    @GetMapping("/chat")
    public ResponseEntity<Collection<DogAdopter>> getByChatId(Long chatId) {
        if (chatId != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(service.getByChatId(chatId));
    }

}
