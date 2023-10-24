package pet.shelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pet.shelter.model.Cat;
import pet.shelter.services.CatService;

import java.util.Collection;

@RestController
@RequestMapping("/cat")
public class CatController {

    private final CatService service;

    public CatController(CatService service) {
        this.service = service;
    }

    @Operation(summary = "Create cat")
    @PostMapping
    public Cat create(@RequestBody Cat cat) {
        return service.create(cat);
    }

    @Operation(summary = "Get cat by id")
    @GetMapping("/{id}")
    public ResponseEntity<Cat> getById(@PathVariable Long id) {
        Cat cat = service.findById(id);
        if (cat == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cat);
    }

    @Operation(summary = "Update cat info")
    @PutMapping("/{id}")
    public ResponseEntity<Cat> update(@PathVariable Long id, Cat cat) {
        Cat foundCat = service.findById(id);
        if (foundCat == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(service.update(id, cat));
    }

    @Operation(summary = "Delete cat by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Cat> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get all cats")
    @GetMapping
    public ResponseEntity<Collection<Cat>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }
}


