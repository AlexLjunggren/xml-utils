package com.ljunggren.xmlUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XmlUtils {

    public static String objectToXml(Object object) throws JsonProcessingException {
        return new XmlMapper().writeValueAsString(object);
    }
    
    public static <T> T xmlToObject(String xml, Class<T> clazz) throws JsonMappingException, JsonProcessingException {
        return new XmlMapper().readValue(xml, clazz);
    }
    
}