package com.mygaienko.common.algorithms.interv.commonrelatives;

import org.apache.commons.collections4.SetUtils;

import java.util.*;

public class RelativeUtil {

    public static boolean isRelative(Human first, Human second) {
        boolean result = false;

        LinkedList<Human> firstRelativesToVisit = new LinkedList<>(first.getParents());
        LinkedList<Human> secondRelativesToVisit = new LinkedList<>(second.getParents());

        Set<Human> firstVisitedRelatives = new HashSet<>();
        Set<Human> secondVisitedRelatives = new HashSet<>();

        while (!result && (!firstRelativesToVisit.isEmpty() || !secondRelativesToVisit.isEmpty())) {

            if (!firstRelativesToVisit.isEmpty()) {
                Human firstRelative = firstRelativesToVisit.pop();
                firstVisitedRelatives.add(firstRelative);
                firstRelativesToVisit.addAll(firstRelative.getParents());
            }

            if (!secondRelativesToVisit.isEmpty()) {
                Human secondRelative = secondRelativesToVisit.pop();
                secondVisitedRelatives.add(secondRelative);
                secondRelativesToVisit.addAll(secondRelative.getParents());
            }

            result = !SetUtils.intersection(firstVisitedRelatives, secondVisitedRelatives).isEmpty();
        }

        return  result;
    }

}
