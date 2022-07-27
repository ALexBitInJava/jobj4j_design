package ru.job4j.io.serialization.xml;


import jakarta.xml.bind.annotation.XmlRootElement;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person2 {
    @XmlAttribute
    private boolean sex;
    @XmlAttribute
    private int age;
    @XmlAttribute
    private Contact contact;
    @XmlAttribute
    private String name;

    private String[] property;

    public Person2(boolean sex, int age, Contact contact, String name, String[] property) {
        this.sex = sex;
        this.age = age;
        this.contact = contact;
        this.name = name;
        this.property = property;
    }

    @Override
    public String toString() {
        return "Person2{" + "sex=" + sex + ", age=" + age
                + ", contact=" + contact + ", name='" + name
                + '\'' + ", statuses=" + Arrays.toString(property)
                + '}';
    }

    public static void main(String[] args) throws Exception {
        final Person2 person2 = new Person2(true, 30, new Contact("88-99-77-55"), "Bob", new String[]{"Car", "House"});
        JAXBContext context = JAXBContext.newInstance(Person2.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        String xml = "";
        try (StringWriter sw = new StringWriter()) {
            marshaller.marshal(person2, sw);
            xml = sw.getBuffer().toString();
            System.out.println(xml);
        }


        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader sr = new StringReader(xml)) {
            Person2 rsl = (Person2) unmarshaller.unmarshal(sr);
            System.out.println(rsl);
        }
    }
}
