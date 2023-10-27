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

    @Operation(summary = "Create user")
    @PostMapping()
    public DogAdopter create(@RequestBody DogAdopter dogAdopter) {
        return service.createAdopter(dogAdopter);
    }

    @Operation(summary = "Get user by id")
    @GetMapping("/{id}")
    public ResponseEntity<DogAdopter> findAdopter(@PathVariable Long id) {
//        DogAdopter foundedAdopter = service.findAdopter(id);
//        if (foundedAdopter == null) {
//            return ResponseEntity.notFound().build();
//        }
        return ResponseEntity.ok(service.findAdopter(id));
    }

    @Operation(summary = "Update user info")
    @PutMapping("/{id}")
    public ResponseEntity<DogAdopter> update(@PathVariable Long id, @RequestBody DogAdopter dogAdopter) {
        if (dogAdopter == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(service.updateAdopter(id, dogAdopter));
    }

    @Operation(summary = "Delete user by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<DogAdopter> delete(@PathVariable Long id) {
        service.deleteAdopter(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get all users")
    @GetMapping("/all")
    public ResponseEntity<Collection<DogAdopter>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Get users by chat id")
    @GetMapping("/chat/{chatId}")
    public ResponseEntity<Collection<DogAdopter>> getByChatId(@PathVariable Long chatId) {
        if (chatId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(service.getByChatId(chatId));
    }

}
