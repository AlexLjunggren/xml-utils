## XML Utils ##

A collection of useful XML utilities

Object to XML

```java
String xml = XmlUtils.objectToXml(myObject);
```

Object to pretty printed XML

```java
String prettyPrinted = XmlUtils.prettyPrint(myObeject);
```

XML to Object

```java
MyClass myObject = XmlUtils.xmlToObject(xml, MyClass.class);
```

Check if String is a valid XML

```java
XmlUtils.isValid(xml);
```

Determine if two XML Strings are equal

```java
XmlUtils.areEqual(xml1, xml2);
```
