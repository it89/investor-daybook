package com.github.it89.investordaybook.model.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.ZonedDateTime;

@Converter(autoApply = true)
public class ZonedDateTimeToStringAttributeConverter implements AttributeConverter<ZonedDateTime, String> {
    @Override
    public String convertToDatabaseColumn(ZonedDateTime zonedDateTime) {
        return (zonedDateTime == null ? null : zonedDateTime.toString());
    }

    @Override
    public ZonedDateTime convertToEntityAttribute(String string) {
        return (string == null ? null : ZonedDateTime.parse(string));
    }
}
