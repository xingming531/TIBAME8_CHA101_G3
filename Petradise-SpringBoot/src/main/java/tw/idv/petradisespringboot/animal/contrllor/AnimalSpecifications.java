package tw.idv.petradisespringboot.animal.contrllor;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;


import tw.idv.petradisespringboot.animal.vo.Animal;

public class AnimalSpecifications {
	
//	public static Specification<Animal> hasAnimalType(String animalType) {
//        return (root, query, builder) -> builder.equal(root.get("animaltype"), animalType);
//    }
//
//    public static Specification<Animal> hasAnimalSex(String animalSex) {
//        return (root, query, builder) -> builder.equal(root.get("animalsex"), animalSex);
//    }
//
//    public static Specification<Animal> hasAnimalAge(String animalAge) {
//        return (root, query, builder) -> builder.equal(root.get("animalage"), animalAge);
//    }

    public static Specification<Animal> createSpecification(String animalType, String animalSex, String animalAge) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            
            if (animalType != null) {
                predicates.add(builder.equal(root.get("animaltype"), animalType));
            }
            
            if (animalSex != null) {
                predicates.add(builder.equal(root.get("animalsex"), animalSex));
            }
            
            if (animalAge != null) {
                predicates.add(builder.equal(root.get("animalage"), animalAge));
            }
            
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
