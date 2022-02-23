package com.wethura.design.filter;

import com.wethura.design.memento.Originator;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FilterTest extends Assert {
    public static final String MALE = "MALE";
    public static final String FEMALE = "FEMALE";
    public static final String SINGLE = "SINGLE";
    public static final String MARRIED = "MARRIED";

    @Test
    public void testFilter() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Lisa", FEMALE, SINGLE));
        persons.add(new Person("Lina", FEMALE, MARRIED));
        persons.add(new Person("Jack", MALE, SINGLE));
        persons.add(new Person("Diana", MALE, MARRIED));
        persons.add(new Person("Mike", MALE, SINGLE));
        persons.add(new Person("Bobby", MALE, SINGLE));
        persons.add(new Person("Gucci", MALE, SINGLE));

        // Female Or Single person
        assertEquals(new OrCriteria(new CriteriaFemale(), new CriteriaSingle()).meetCriteria(persons).size(), 6);

        // Female And Single person
        assertEquals(new AndCriteria(new CriteriaFemale(), new CriteriaSingle()).meetCriteria(persons).size(), 1);
    }
}
