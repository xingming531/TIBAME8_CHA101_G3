package tw.idv.petradisespringboot.pet.vo.enums;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class PetTypeConverter implements AttributeConverter<PetType, String> {

    @Override
    public String convertToDatabaseColumn(PetType attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public PetType convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return PetType.fromValue(dbData);
    }
}
