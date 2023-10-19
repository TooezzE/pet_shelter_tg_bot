package pet.shelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pet.shelter.model.Cat;
import pet.shelter.model.Dog;
import pet.shelter.services.CatService;
import pet.shelter.services.DogService;

import java.util.Collection;

@RestController
@RequestMapping("/dog")
public class DogController {

    private final DogService service;

    public DogController(DogService service) {
        this.service = service;
    }

    @Operation(summary = "Создание dog")
    @PostMapping
    public Dog create(@RequestBody Dog dog) {
        return service.create(dog);
    }

    @Operation(summary = "Получение dog by id")
    @GetMapping("/{id}")
    public ResponseEntity<Dog> getById(@PathVariable Long id) {
        Dog dog = service.findById(id);
        if (dog == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "Обновление данных dog")
    @PutMapping
    public ResponseEntity<Dog> update(@PathVariable Long id, Dog dog) {
        Dog foundDog = service.create(dog);
        if (foundDog == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(service.update(dog));
    }

    @Operation(summary = "Удаление dog by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Dog> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Получение всех Dog")
    @GetMapping
    public ResponseEntity<Collection<Dog>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }
}


