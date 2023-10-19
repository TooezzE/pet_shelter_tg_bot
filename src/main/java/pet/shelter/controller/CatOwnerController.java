package pet.shelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pet.shelter.model.CatOwners;
import pet.shelter.services.CatOwnerService;

import java.util.Collection;

@RestController
@RequestMapping("/owner-cat")
public class CatOwnerController {
    private final CatOwnerService service;

    public CatOwnerController(CatOwnerService service) {
        this.service = service;
    }

    @Operation(summary = "Создание пользователя")
    @PostMapping()
    public CatOwners create(@RequestBody CatOwners catOwners) {
        return service.createOwner(catOwners);
    }

    @Operation(summary = "Получение пользователя по id")
    @GetMapping("/{id}")
    public ResponseEntity<CatOwners> findOwner(@PathVariable Long id) {
        CatOwners owners = service.findOwner(id);
        if (owners == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.findOwner(id));
    }

    @Operation(summary = "Обновление данных пользователя")
    @PutMapping
    public ResponseEntity<CatOwners> update(@RequestBody CatOwners catOwners) {
        CatOwners foundOwners = service.createOwner(catOwners);
        if (foundOwners == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(service.updateOwner(catOwners));
    }

    @Operation(summary = "Удаление пользователей по id")
    @DeleteMapping("/{id}")
    public ResponseEntity<CatOwners> delete(@PathVariable Long id) {
        service.deleteOwner(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Получение всех пользователей")
    @GetMapping("/all")
    public ResponseEntity<Collection<CatOwners>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Получение всех пользователей")
    @GetMapping("/chat")
    public ResponseEntity<Collection<CatOwners>> getByChatId(Long chatId) {
        if (chatId != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(service.getByChatId(chatId));
    }

}
