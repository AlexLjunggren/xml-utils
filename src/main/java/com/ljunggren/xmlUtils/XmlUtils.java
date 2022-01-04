package com.ljunggren.xmlUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XmlUtils {

    public static String objectToXml(Object object) throws JsonProcessingException {
        return objectToXml(object, new XmlMapper());
    }
    
    public static String prettyPrint(Object object) throws JsonProcessingException {
        XmlMapper mapper = new XmlMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return objectToXml(object, mapper);
    }
    
    private static String objectToXml(Object object, XmlMapper mapper) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }
    
    public static <T> T xmlToObject(String xml, Class<T> clazz) throws JsonMappingException, JsonProcessingException {
        return new XmlMapper().readValue(xml, clazz);
    }
    
    public static boolean isValid(String xml) {
        try {
            new XmlMapper().readTree(xml);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static boolean areEqual(String xml1, String xml2) throws JsonMappingException, JsonProcessingException {
        XmlMapper mapper = new XmlMapper();
        return mapper.readTree(xml1).equals(mapper.readTree(xml2));
    }
    
}
