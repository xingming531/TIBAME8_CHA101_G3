package tw.idv.petradisespringboot.pet.service;

import tw.idv.petradisespringboot.pet.dto.NewPetDTO;
import tw.idv.petradisespringboot.pet.dto.PetOptionsDTO;
import tw.idv.petradisespringboot.pet.vo.Pet;

import java.util.List;
import java.util.Optional;

public interface PetService {

    List<Pet> getAll();

    List<Pet> getPetsByMemId(Integer memId);

    Optional<Pet> getPetById(Integer id);

    Pet addPet(NewPetDTO dto);

    Pet updatePet(Pet pet);

    void deletePet(Integer id);

    PetOptionsDTO getPetOptions();
}
