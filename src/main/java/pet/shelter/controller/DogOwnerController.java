package pet.shelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pet.shelter.model.DogOwners;
import pet.shelter.services.DogOwnerService;

import java.util.Collection;

@RestController
@RequestMapping("/owner-dog")
public class DogOwnerController {
    private final DogOwnerService service;

    public DogOwnerController(DogOwnerService service) {
        this.service = service;
    }

    @Operation(summary = "Создание пользователя")
    @PostMapping()
    public DogOwners create(@RequestBody DogOwners dogOwners) {
        return service.createOwner(dogOwners);
    }

    @Operation(summary = "Получение пользователя по id")
    @GetMapping("/{id}")
    public ResponseEntity<DogOwners> findOwner(@PathVariable Long id) {
        DogOwners owners = service.findOwner(id);
        if (owners == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.findOwner(id));
    }

    @Operation(summary = "Обновление данных пользователя")
    @PutMapping
    public ResponseEntity<DogOwners> update(@RequestBody DogOwners dogOwners) {
        DogOwners foundOwners = service.createOwner(dogOwners);
        if (foundOwners == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(service.updateOwner(dogOwners));
    }

    @Operation(summary = "Удаление пользователей по id")
    @DeleteMapping("/{id}")
    public ResponseEntity<DogOwners> delete(@PathVariable Long id) {
        service.deleteOwner(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Получение всех пользователей")
    @GetMapping("/all")
    public ResponseEntity<Collection<DogOwners>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Получение всех пользователей")
    @GetMapping("/chat")
    public ResponseEntity<Collection<DogOwners>> getByChatId(Long chatId) {
        if (chatId != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(service.getByChatId(chatId));
    }

}
