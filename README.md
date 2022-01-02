## XML Utils ##

A collection of useful XML utilities

Object to XML

```java
String xml = XmlUtils.objectToXml(myObject);
```

XML to Object

```java
MyClass myObject = XmlUtils.xmlToObject(xml, MyClass.class);
```

Determine if two XML Strings are equal

```java
XmlUtils.areEqual(xml1, xml2);
```
