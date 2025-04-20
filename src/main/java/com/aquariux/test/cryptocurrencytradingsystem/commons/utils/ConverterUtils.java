package com.aquariux.test.cryptocurrencytradingsystem.commons.utils;

import com.aquariux.test.cryptocurrencytradingsystem.commons.exceptions.TradingSystemException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class ConverterUtils {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        OBJECT_MAPPER.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
    }

    public <T> T convertStrToObject(String str, TypeReference<T> valueTypeRef) {
        try {
            return OBJECT_MAPPER.readValue(str, valueTypeRef);
        } catch (JsonProcessingException e) {
            log.warn("Could not convert error to object: ", e);
            return null;
        }
    }

    public <T> T convertStrToObject(String str, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(str, clazz);
        } catch (JsonProcessingException e) {
            log.warn("Could not convert error to object: ", e);
            return null;
        }
    }

    public <T> String convertObjectToStr(T data) {
        OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        try {
            return OBJECT_MAPPER.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new TradingSystemException("System error");
        }
    }

    public <I, O> O convertObjectToObject(I input, Class<O> outputClass) {
        OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        return OBJECT_MAPPER.convertValue(input, outputClass);
    }
}