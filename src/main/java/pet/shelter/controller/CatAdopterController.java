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

    @Operation(summary = "Create user")
    @PostMapping()
    public CatAdopter create(@RequestBody CatAdopter catAdopters) {
        return service.createAdopter(catAdopters);
    }

    @Operation(summary = "Get user by id")
    @GetMapping("/{id}")
    public ResponseEntity<CatAdopter> findAdopter(@PathVariable Long id) {
        CatAdopter foundedAdopter = service.findAdopter(id);
        if (foundedAdopter == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.findAdopter(id));
    }

    @Operation(summary = "Update user info")
    @PutMapping("/{id}")
    public ResponseEntity<CatAdopter> update(@PathVariable Long id, @RequestBody CatAdopter catAdopters) {
        CatAdopter foundAdopters = service.findAdopter(id);
        if (foundAdopters == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(service.updateAdopter(id, catAdopters));
    }

    @Operation(summary = "Delete user by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<CatAdopter> delete(@PathVariable Long id) {
        service.deleteAdopter(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get all users")
    @GetMapping("/all")
    public ResponseEntity<Collection<CatAdopter>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Get users by chat id")
    @GetMapping("/chat/{chatId}")
    public ResponseEntity<Collection<CatAdopter>> getByChatId(@PathVariable Long chatId) {
        if (chatId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(service.getByChatId(chatId));
    }

}
