package pet.shelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pet.shelter.model.Dog;
import pet.shelter.services.DogService;

import java.util.Collection;

@RestController
@RequestMapping("/dog")
@Tag(name = "Dog Controller", description = "Making CRUD operations with dogs")
public class DogController {

    private final DogService service;

    public DogController(DogService service) {
        this.service = service;
    }

    @Operation(summary = "Create dog")
    @PostMapping
    public Dog create(@RequestBody Dog dog) {
        return service.create(dog);
    }

    @Operation(summary = "Get dog by id")
    @GetMapping("/{id}")
    public ResponseEntity<Dog> getById(@PathVariable Long id) {
        Dog dog = service.findById(id);
        if (dog == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "Update dog info")
    @PutMapping("/{id}")
    public ResponseEntity<Dog> update(@PathVariable Long id,@RequestBody Dog dog) {
        if (dog == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(service.update(id, dog));
    }

    @Operation(summary = "Delete dog by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Dog> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get all dogs")
    @GetMapping
    public ResponseEntity<Collection<Dog>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }
}


