package com.mygaienko.practice.jpa.model.graph;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by dmygaenko on 28/01/2016.
 */
@NodeEntity
public class Comment {

    @GraphId
    Long id;
    public String name;

    @Relationship(type="SUBCOMMENTS", direction = Relationship.OUTGOING)
    public Set<Comment> subComments;

    public void addComment(Comment comment) {
        if (subComments == null) {
            subComments = new HashSet<Comment>();
        }
        subComments.add(comment);
    }
}
