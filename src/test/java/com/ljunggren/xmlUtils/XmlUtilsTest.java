package com.ljunggren.xmlUtils;

import static org.junit.Assert.*;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class XmlUtilsTest {
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class User {
        private String name;
        private int age;
        @JacksonXmlProperty(isAttribute = true)
        private boolean active;
    }

    @Test
    public void objectToXmlTest() throws JsonProcessingException {
        User user = new User("Alex", 40, true);
        String expected = "<User active=\"true\"><name>Alex</name><age>40</age></User>";
        String actual = XmlUtils.objectToXml(user);
        assertEquals(expected, actual);
    }
    
    @Test
    public void xmlToObjectTest() throws JsonMappingException, JsonProcessingException {
        User user = new User("Alex", 40, true);
        String xml = XmlUtils.objectToXml(user);
        User generatedUser = XmlUtils.xmlToObject(xml, User.class);
        assertEquals(user, generatedUser);
    }

}
