package tw.idv.petradisespringboot.admin.vo.converter;


import tw.idv.petradisespringboot.admin.vo.enums.AdminTitle;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class AdminTitleConverter implements AttributeConverter<AdminTitle, String> {
    @Override
    public String convertToDatabaseColumn(AdminTitle attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public AdminTitle convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return AdminTitle.getByValue(dbData);
    }
}
