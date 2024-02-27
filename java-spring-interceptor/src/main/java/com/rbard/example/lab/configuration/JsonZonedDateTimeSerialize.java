package com.rbard.example.lab.configuration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class JsonZonedDateTimeSerialize extends JsonSerializer<ZonedDateTime> {

  @Override
  public void serialize(ZonedDateTime value, JsonGenerator jsonGenerator,
      SerializerProvider serializerProvider) throws IOException {
    DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
    jsonGenerator.writeString(value.format(formatter));
  }

}
