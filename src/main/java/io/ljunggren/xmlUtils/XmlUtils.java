package io.ljunggren.xmlUtils;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

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
        if (object instanceof String) {
            return prettyPrintString((String) object, true);
        }
        return objectToXml(object, mapper);
    }
    
    private static String prettyPrintString(String xml, boolean ignoreDeclaration) {
        try {
            InputSource src = new InputSource(new StringReader(xml));
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(src);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", 4);
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, ignoreDeclaration ? "yes" : "no");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            Writer out = new StringWriter();
            transformer.transform(new DOMSource(document), new StreamResult(out));
            return out.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error occurs when pretty-printing xml:\n" + xml, e);
        }
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
