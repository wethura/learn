package com.wethura.design.filter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OrCriteria implements Criteria {
    protected final Criteria[] criteria;

    public OrCriteria(Criteria... criteria) {
        this.criteria = criteria;
    }

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        Set<Person> results = new HashSet<>();
        for (Criteria criterion : criteria) {
            results.addAll(criterion.meetCriteria(persons));
        }
        return new ArrayList<>(results);
    }
}
