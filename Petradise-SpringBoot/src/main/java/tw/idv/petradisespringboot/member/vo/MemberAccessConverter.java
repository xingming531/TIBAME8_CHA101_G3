package tw.idv.petradisespringboot.member.vo;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class MemberAccessConverter implements AttributeConverter<MemberAccess, String> {
    @Override
    public String convertToDatabaseColumn(MemberAccess attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public MemberAccess convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return MemberAccess.fromValue(dbData);
    }
}
