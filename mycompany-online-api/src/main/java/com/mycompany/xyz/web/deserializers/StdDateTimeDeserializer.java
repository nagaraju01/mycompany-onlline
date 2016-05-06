package com.mycompany.xyz.web.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * StdDateTimeDeserializer class
 *
 * @author nagarajut
 */
public class StdDateTimeDeserializer extends JsonDeserializer<DateTime> {

    /**
     * Standard format
     */
    private static DateTimeFormatter formatter = DateTimeFormat
            .forPattern("yyyy/MM/dd HH:mm:ss");

    @Override
    public DateTime deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {

        String value = jp.getText();
        if (StringUtils.isBlank(value)) {
            return null;
        }

        return formatter.parseDateTime(value);
    }
}
