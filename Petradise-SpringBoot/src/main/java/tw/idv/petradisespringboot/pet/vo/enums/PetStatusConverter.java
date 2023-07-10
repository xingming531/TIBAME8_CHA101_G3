package tw.idv.petradisespringboot.pet.vo.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class PetStatusConverter implements AttributeConverter<PetStatus, String> {

    @Override
    public String convertToDatabaseColumn(PetStatus petStatus) {
        return petStatus.getValue();
    }

    @Override
    public PetStatus convertToEntityAttribute(String value) {
        return PetStatus.fromValue(value);
    }
}
