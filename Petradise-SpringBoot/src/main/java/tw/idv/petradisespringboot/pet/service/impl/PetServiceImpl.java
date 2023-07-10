package tw.idv.petradisespringboot.pet.service.impl;

import org.springframework.stereotype.Service;
import tw.idv.petradisespringboot.pet.dto.PetOptionsDTO;
import tw.idv.petradisespringboot.pet.repo.PetPicRepository;
import tw.idv.petradisespringboot.pet.repo.PetRepository;
import tw.idv.petradisespringboot.pet.service.PetService;
import tw.idv.petradisespringboot.pet.dto.NewPetDTO;
import tw.idv.petradisespringboot.pet.vo.Pet;
import tw.idv.petradisespringboot.pet.vo.PetPic;
import tw.idv.petradisespringboot.pet.vo.enums.PetSize;
import tw.idv.petradisespringboot.pet.vo.enums.PetStatus;
import tw.idv.petradisespringboot.pet.vo.enums.PetType;

import javax.transaction.Transactional;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    private final PetPicRepository petPicRepository;

    public PetServiceImpl(PetRepository petRepository, PetPicRepository petPicRepository) {
        this.petRepository = petRepository;
        this.petPicRepository = petPicRepository;
    }

    @Override
    public List<Pet> getAll() {
        return petRepository.findAllByStatus(PetStatus.NORMAL);
    }

    @Override
    public List<Pet> getPetsByMemId(Integer memId) {
        return petRepository
                .findByMemberIdAndStatus(memId, PetStatus.NORMAL);
    }

    @Override
    public Optional<Pet> getPetById(Integer id) {
        return petRepository.findByIdAndStatus(id, PetStatus.NORMAL);
    }

    @Transactional
    @Override
    public Pet addPet(NewPetDTO dto) {
        final var pet = dto.getPet();
        final var newPet = petRepository.save(pet);
        var petPics = dto
                .getPics()
                .stream()
                .map(pic -> convertAndSavePic(pic, newPet))
                .collect(Collectors.toList());
        newPet.setPetPics(petPics);
        return newPet;
    }

    private PetPic convertAndSavePic(String pic, Pet newPet) {
        var byteArray = Base64.getDecoder().decode(pic);
        var petPic = new PetPic();
        petPic.setPet(newPet);
        petPic.setPic(byteArray);
        return petPicRepository.save(petPic);
    }

    @Transactional
    @Override
    public Pet updatePet(Pet pet) {
        if (!petRepository.existsById(pet.getId())) {
            return pet;
        }
        return petRepository.save(pet);
    }

    @Transactional
    @Override
    public void deletePet(Integer id) {
        var pet = petRepository.findById(id);
        if (pet.isPresent()) {
            pet.get().setStatus(PetStatus.DELETED);
            petRepository.save(pet.get());
        }
    }

    @Override
    public PetOptionsDTO getPetOptions() {
        return new PetOptionsDTO(PetType.values(), PetSize.values());
    }
}
