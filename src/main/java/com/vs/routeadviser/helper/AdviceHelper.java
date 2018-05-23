package com.vs.routeadviser.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;
import com.vs.routeadviser.model.Advice;
import com.vs.routeadviser.model.Category;
import com.vs.routeadviser.model.Place;

public class AdviceHelper {

    public List<Advice> advisePlaces(List<Category> categories, List<String> tags, List<Place> places) {
        List<Advice> advices = new ArrayList<>();
        for (Place place : places) {
            Advice advice = createAdviceFrom(Sets.newHashSet(categories), Sets.newHashSet(tags), place);
            if (advice.getScore() > 0) {
                advices.add(advice);

            }
        }
        return advices;
    }

    private Advice createAdviceFrom(Set<Category> categories, Set<String> tags, Place place) {
        int categoryScore = Sets.intersection(place.getCategories(), categories).size();
        int tagScore = Sets.intersection(place.getTags(), tags).size();
        return new Advice(categoryScore + tagScore, place);
    }

}
