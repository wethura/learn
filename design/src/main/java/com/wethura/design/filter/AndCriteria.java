package com.wethura.design.filter;

import java.util.List;
import java.util.stream.Stream;

public class AndCriteria implements Criteria{

    protected final Criteria[] criteria;

    public AndCriteria(Criteria... criteria) {
        this.criteria = criteria;
    }

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        for (Criteria criterion : criteria) {
            persons = criterion.meetCriteria(persons);
        }

        return persons;
    }
}
