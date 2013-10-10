package br.jus.tre_pa.frameworkdemoiselle.query.filter.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import br.jus.tre_pa.frameworkdemoiselle.query.filter.app.model.Address;
import br.jus.tre_pa.frameworkdemoiselle.query.filter.app.model.Car;
import br.jus.tre_pa.frameworkdemoiselle.query.filter.app.model.Cat;
import br.jus.tre_pa.frameworkdemoiselle.query.filter.app.model.Certification;
import br.jus.tre_pa.frameworkdemoiselle.query.filter.app.model.Color;
import br.jus.tre_pa.frameworkdemoiselle.query.filter.app.model.Dog;
import br.jus.tre_pa.frameworkdemoiselle.query.filter.app.model.Person;

public class CodeGenerator {
    public static final String PERSON01_NAME = "John";

    public static final String PERSON01_NICKNAME = "Little John";
    public static final String PERSON02_NAME = "Mary";

    public static final String PERSON03_NAME = "Others attributes will be null";
    public static final String PERSON04_NAME = "Others attributes will be null";
    public static final String PERSON05_NAME = "joseph";
    public static final String PERSON06_NAME = "paul";
    public static final String NICKNAME_A_NAME = "NickName A";
    public static final String NICKNAME_B_NAME = "NickName B";

    public static final String STREET_NAME_A = "Street A";
    public static final String STREET_NAME_B = "Street B";

    public static void generateData(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        TypedQuery<Person> query = entityManager.createQuery("select p from Person p", Person.class);

        // EclipseLink was invoking two times
        if(query.getResultList().isEmpty()){
            generatePersons(entityManagerFactory);
            generateAddresses(entityManagerFactory);
            generateCars(entityManagerFactory);
            generateDogs(entityManagerFactory);
            generateCats(entityManagerFactory);
            generateCertifications(entityManagerFactory);
            createRelationShipBetweenEntities(entityManagerFactory);
        }
    }


    private static Calendar createCalendar(int day, int month, int year) {
        Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.setTime(createNewDate(day, month, year));

        return dateOfBirth;
    }

    private static void generateCertifications(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Certification certification1 = new Certification(1, "SCJP");
        Certification certification2 = new Certification(2, "SCWCD");
        Certification certification3 = new Certification(3, "OCBCD");
        Certification certification4 = new Certification(4, "OCJPAD");

        entityManager.persist(certification1);
        entityManager.persist(certification2);
        entityManager.persist(certification3);
        entityManager.persist(certification4);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static void generateCats(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Cat cat1 = new Cat(1, "Freud");
        Cat cat2 = new Cat(2, "Marx");

        entityManager.persist(cat1);
        entityManager.persist(cat2);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static void generateDogs(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Dog dog1 = new Dog(1, "Ice", 13.00d, createNewDate(1, 1, 2009), 13.00f, 13L, 13);
        Dog dog2 = new Dog(2, "Fire", 5.00d, createNewDate(1, 1, 2010), 5.00f, 5L, 5);
        Dog dog3 = new Dog(3, "Thunder", 6.00d, createNewDate(1, 1, 2011), 4.00f, 6L, 7);
        Dog dog4 = new Dog(4, "Earth", 2.00d, createNewDate(1, 1, 2012), 2.00f, 2L, 3);

        entityManager.persist(dog1);
        entityManager.persist(dog2);
        entityManager.persist(dog3);
        entityManager.persist(dog4);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static void generateCars(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Car car1 = new Car(1, "Dark Horse", new Color(1, "White"), new BigDecimal(20.0));
        Car car2 = new Car(2, "Blue Arrow", new Color(2, "Red"), new BigDecimal(30.0));
        Car car3 = new Car(3, "Yellow Submarine", null, new BigDecimal(30.0));

        entityManager.persist(car1);
        entityManager.persist(car2);
        entityManager.persist(car3);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static void createRelationShipBetweenEntities(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Person person01 = entityManager.find(Person.class, 1);
        Person person02 = entityManager.find(Person.class, 2);
        Person person06 = entityManager.find(Person.class, 6);

        Address address01 = entityManager.find(Address.class, 1);
        Address address02 = entityManager.find(Address.class, 2);

        Car car1 = entityManager.find(Car.class, 1);
        Car car2 = entityManager.find(Car.class, 2);
        Car car3 = entityManager.find(Car.class, 3);

        Cat cat1 = entityManager.find(Cat.class, 1);
        Cat cat2 = entityManager.find(Cat.class, 2);

        Certification certification1 = entityManager.find(Certification.class, 1);
        Certification certification2 = entityManager.find(Certification.class, 2);

        Dog dog1 = entityManager.find(Dog.class, 1);
        Dog dog2 = entityManager.find(Dog.class, 2);
        Dog dog3 = entityManager.find(Dog.class, 3);
        Dog dog6 = entityManager.find(Dog.class, 4);

        Color color1 = entityManager.find(Color.class, 1);
        Color color2 = entityManager.find(Color.class, 2);

        person01.setAddress(address01);
        person01.setCar(car1);

        person02.setAddress(address02);
        person02.setCar(car2);

        dog1.setPerson(person01);
        dog2.setPerson(person01);

        person01.getDogs().add(dog1);
        person01.getDogs().add(dog2);

        dog3.setPerson(person02);
        person02.getDogs().add(dog3);

        cat1.setPerson(person01);
        cat2.setPerson(person01);

        person01.getCats().add(cat1);
        person01.getCats().add(cat2);

        certification1.setPerson(person02);
        certification2.setPerson(person02);

        person02.getCertifications().add(certification1);
        person02.getCertifications().add(certification2);

        person06.getDogs().add(dog6);
        dog6.setPerson(person06);
        person06.setCar(car3);
        car3.setPerson(person06);

        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static void generateAddresses(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Date buildingDate1 = createNewDate(1, 1, 1980);
        Address address1 = new Address(1, STREET_NAME_A, 34, true, false, buildingDate1);

        Date buildingDate2 = createNewDate(1, 1, 1990);
        Address address2 = new Address(2, STREET_NAME_B, 4, false, true, buildingDate2);

        entityManager.persist(address1);
        entityManager.persist(address2);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static void generatePersons(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Date firstJobDate1 = createNewDate(1, 1, 2015);
        Date firstSoccerMatchDate1 = createNewDate(1, 1, 2013);

        Date birthDay1 = createNewDate(1, 1, 2001);
        Calendar birthDayPerson01 = Calendar.getInstance();
        birthDayPerson01.setTime(birthDay1);
        Calendar firstKissPerson01 = Calendar.getInstance();
        firstKissPerson01.setTime(birthDay1);
        firstKissPerson01.set(Calendar.YEAR, 2011);

        Person person1 = new Person(1, PERSON01_NAME, PERSON01_NICKNAME, 33, birthDayPerson01, firstKissPerson01, 10.00f, 10.00d, 1928371L, 10, 20L, firstJobDate1, firstSoccerMatchDate1, true, false);

        Date birthDay2 = createNewDate(1, 1, 2002);
        Date firstJobDate2 = createNewDate(1, 1, 2016);
        Date firstSoccerMatchDate2 = createNewDate(1, 1, 2015);

        Calendar birthDayPerson02 = Calendar.getInstance();
        birthDayPerson02.setTime(birthDay2);
        Calendar firstKissPerson02 = Calendar.getInstance();
        firstKissPerson02.setTime(birthDay2);
        firstKissPerson02.set(Calendar.YEAR, 2011);

        Person person2 = new Person(2, PERSON02_NAME, "Hello Mary", 44, birthDayPerson02, firstKissPerson02, 11.00f, 11.00d, 98723487L, 20, 30L, firstJobDate2, firstSoccerMatchDate2, false, true);

        Person person3 = new Person();
        person3.setId(3);
        person3.setName(PERSON03_NAME);

        Person person4 = new Person();
        person4.setId(4);
        person4.setName(PERSON04_NAME);

        Person person5 = new Person();
        person5.setId(5);
        person5.setName(PERSON05_NAME);

        Person person6 = new Person();
        person6.setId(6);
        person6.setName(PERSON06_NAME);

        entityManager.persist(person1);
        entityManager.persist(person2);
        entityManager.persist(person3);
        entityManager.persist(person4);
        entityManager.persist(person5);
        entityManager.persist(person6);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static Date createNewDate(int day, int month, int year) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        try {
            return formatter.parse("" + day + "/" + month + "/" + year);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}