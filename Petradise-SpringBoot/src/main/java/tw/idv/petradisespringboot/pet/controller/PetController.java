package tw.idv.petradisespringboot.pet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.idv.petradisespringboot.pet.service.PetService;
import tw.idv.petradisespringboot.pet.dto.NewPetDTO;
import tw.idv.petradisespringboot.pet.vo.Pet;

@RestController
@RequestMapping("/pets")
public class PetController {

    private final PetService service;

    public PetController(PetService service) {
        this.service = service;
    }

    @GetMapping("/all")
    ResponseEntity<?> all() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping("/add")
    ResponseEntity<?> newPet(@RequestBody NewPetDTO dto) {
        try {
            service.addPet(dto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/update")
    ResponseEntity<?> updatePet(@RequestBody Pet pet) {
        return ResponseEntity.ok(service.updatePet(pet));
    }

    @GetMapping("/id={id}")
    ResponseEntity<?> one(@PathVariable Integer id) {
        var pet = service.getPetById(id);
        if (pet.isPresent()) {
            return ResponseEntity.ok(pet.get());
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new PetNotFoundException(id));
    }

    @GetMapping("/memberId={memberId}")
    ResponseEntity<?> getPetsByMemberId(@PathVariable Integer memberId) {
        return ResponseEntity.ok(service.getPetsByMemId(memberId));
    }

    @DeleteMapping("/delete/id={id}")
    ResponseEntity<?> deletePetById(@PathVariable Integer id) {
        try {
            service.deletePet(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new PetNotFoundException(id));
        }
    }

    @GetMapping("/options")
    ResponseEntity<?> getPetOptions() {
        return ResponseEntity.ok(service.getPetOptions());
    }

}

class PetNotFoundException extends RuntimeException {

    PetNotFoundException(Integer id) {
        super("找不到寵物ID: " + id);
    }

}