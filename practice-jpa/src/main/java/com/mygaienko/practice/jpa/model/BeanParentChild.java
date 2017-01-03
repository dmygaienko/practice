package com.mygaienko.practice.jpa.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by mygadmy on 03/12/15.
 */
@Entity(name = "bean_parent_child")
public class BeanParentChild {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private BeanParentChild parent;

    @OneToMany(mappedBy = "parent")
    private List<BeanParentChild> children;

    public BeanParentChild(){}

    public BeanParentChild(long id) {
        this.id = id;
    }

    public BeanParentChild(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public BeanParentChild getParent() {
        return parent;
    }

    public void setParent(BeanParentChild parent) {
        this.parent = parent;
    }

    public List<BeanParentChild> getChildren() {
        return children;
    }

    public void setChildren(List<BeanParentChild> children) {
        this.children = children;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
