package tw.idv.petradisespringboot.hotel_owner.vo;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class HotelOwnerAccessConverter implements AttributeConverter<HotelOwnerAccess, String> {
    @Override
    public String convertToDatabaseColumn(HotelOwnerAccess attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public HotelOwnerAccess convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return HotelOwnerAccess.fromValue(dbData);
    }

}
