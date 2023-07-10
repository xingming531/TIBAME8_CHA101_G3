package tw.idv.petradisespringboot.pet.vo.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class PetSizeConverter implements AttributeConverter<PetSize, String> {

    @Override
    public String convertToDatabaseColumn(PetSize petSize) {
        return petSize.getValue();
    }

    @Override
    public PetSize convertToEntityAttribute(String value) {
        return PetSize.fromValue(value);
    }

}
