package tw.idv.petradisespringboot.admin.vo.converter;

import tw.idv.petradisespringboot.admin.vo.enums.AdminStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class AdminStatusConverter implements AttributeConverter<AdminStatus, String> {

    @Override
    public String convertToDatabaseColumn(AdminStatus attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public AdminStatus convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return AdminStatus.getByValue(dbData);
    }
}
