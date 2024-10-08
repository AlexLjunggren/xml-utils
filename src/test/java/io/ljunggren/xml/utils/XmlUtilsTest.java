package io.ljunggren.xml.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
    public void prettyPrintTest() throws JsonProcessingException {
        User user = new User("Alex", 40, true);
        String expected = "<User active=\"true\">\r\n"
                + "  <name>Alex</name>\r\n"
                + "  <age>40</age>\r\n"
                + "</User>\r\n";
        String actual = XmlUtils.prettyPrint(user);
        assertEquals(expected, actual);
    }
    
    @Test
    public void xmlToObjectTest() throws JsonMappingException, JsonProcessingException {
        User user = new User("Alex", 40, true);
        String xml = XmlUtils.objectToXml(user);
        User generatedUser = XmlUtils.xmlToObject(xml, User.class);
        assertEquals(user, generatedUser);
    }
    
    @Test
    public void isValidTest() throws JsonProcessingException {
        User user = new User("Alex", 40, true);
        String xml = XmlUtils.objectToXml(user);
        assertTrue(XmlUtils.isValid(xml));
    }
    
    @Test
    public void isValidNullTest() {
        assertFalse(XmlUtils.isValid(null));
    }
    
    @Test
    public void isValidFalseTest() {
        assertFalse(XmlUtils.isValid("<User"));
    }
    
    @Test
    public void areEqualTest() throws JsonMappingException, JsonProcessingException {
        User user = new User("Alex", 40, true);
        String expected = "<User active=\"true\"><name>Alex</name><age>40</age></User>";
        String actual = XmlUtils.objectToXml(user);
        assertTrue(XmlUtils.areEqual(expected, actual));
    }

    @Test
    public void areEqualFalseTest() throws JsonMappingException, JsonProcessingException {
        User user = new User("Alex", 40, true);
        String expected = "<User active=\"true\"><name>Alexander</name><age>40</age></User>";
        String actual = XmlUtils.objectToXml(user);
        assertFalse(XmlUtils.areEqual(expected, actual));
    }

}
