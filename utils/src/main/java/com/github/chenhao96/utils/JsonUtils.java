package com.github.chenhao96.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * make by ChenHao on 2016/5/27.
 */
public class JsonUtils {

    private static final ObjectMapper mapper = defaultObjectMapper();
    private static final ObjectMapper stringMapper = defaultObjectStringMapper();

    public static String object2Json(Object obj) throws JsonProcessingException {
        return mapper.writeValueAsString(obj);
    }

    public static <T> T jsonStr2Object(String jsonStr, Class<T> clazz) throws IOException {
        return mapper.readValue(jsonStr, clazz);
    }

    public static JsonNode jsonStr2JsonNode(String jsonStr) throws IOException {
        return mapper.readTree(jsonStr);
    }

    public static <T> T jsonStr2TypeReference(String jsonStr, TypeReference<T> valueTypeRef) throws IOException {
        return mapper.readValue(jsonStr, valueTypeRef);
    }

    public static String object2JsonUseStringValue(Object obj) throws JsonProcessingException {
        return stringMapper.writeValueAsString(obj);
    }

    public static ObjectMapper defaultObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        return mapper;
    }

    public static ObjectMapper defaultObjectStringMapper() {
        ObjectMapper stringMapper = defaultObjectMapper();
        stringMapper.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, true);
        return stringMapper;
    }
}
