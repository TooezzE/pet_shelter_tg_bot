package pet.shelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pet.shelter.model.CatAdopter;
import pet.shelter.services.CatAdopterService;

import java.util.Collection;

@RestController
@RequestMapping("/adopter-cat")
public class CatAdopterController {
    private final CatAdopterService service;

    public CatAdopterController(CatAdopterService service) {
        this.service = service;
    }

    @Operation(summary = "Создание пользователя")
    @PostMapping()
    public CatAdopter create(@RequestBody CatAdopter catAdopters) {
        return service.createAdopter(catAdopters);
    }

    @Operation(summary = "Получение пользователя по id")
    @GetMapping("/{id}")
    public ResponseEntity<CatAdopter> findAdopter(@PathVariable Long id) {
        CatAdopter owners = service.findAdopter(id);
        if (owners == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.findAdopter(id));
    }

    @Operation(summary = "Обновление данных пользователя")
    @PutMapping
    public ResponseEntity<CatAdopter> update(@RequestBody CatAdopter catAdopters) {
        CatAdopter foundAdopters = service.createAdopter(catAdopters);
        if (foundAdopters == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(service.updateAdopter(catAdopters));
    }

    @Operation(summary = "Удаление пользователей по id")
    @DeleteMapping("/{id}")
    public ResponseEntity<CatAdopter> delete(@PathVariable Long id) {
        service.deleteAdopter(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Получение всех пользователей")
    @GetMapping("/all")
    public ResponseEntity<Collection<CatAdopter>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Получение всех пользователей")
    @GetMapping("/chat")
    public ResponseEntity<Collection<CatAdopter>> getByChatId(Long chatId) {
        if (chatId != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(service.getByChatId(chatId));
    }

}
