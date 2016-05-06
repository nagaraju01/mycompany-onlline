/*
 * Copyright  ©  My Company Ltd. All Rights Reserved.
 */
package com.mycompany.xyz.web.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.mycompany.xyz.util.DateUtils;
import java.io.IOException;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Serialize Joda's {@link DateTime} to string
 *
 * @author nagarajut
 */
public class DateTimeSerializer extends JsonSerializer<DateTime> {

    private final static DateTimeFormatter formatter = DateTimeFormat.forPattern(DateUtils.DATE_FORMAT_SECONDS);

    @Override
    public void serialize(DateTime dateTime, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException, JsonProcessingException {
        if (dateTime == null) {
            jsonGenerator.writeString("");
        } else {
            jsonGenerator.writeString(dateTime.toString(formatter));
        }
    }

}
