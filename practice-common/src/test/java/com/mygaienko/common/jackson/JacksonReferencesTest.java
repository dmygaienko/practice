package com.mygaienko.common.jackson;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class JacksonReferencesTest {

    @Test
    public void test() throws JsonProcessingException {
        D d = new D();
        d.setD1("v1");

        C c = new C();
        c.addD(d);

        System.out.println(new ObjectMapper().writeValueAsString(c));
        System.out.println(new ObjectMapper().writeValueAsString(d));
    }
}

class C {

    @JsonManagedReference
    private List<D> ds = new ArrayList<>();

    public List<D> getDs() {
        return ds;
    }

    public void setDs(List<D> ds) {
        this.ds = ds;
    }

    public void addD(D d) {
        ds.add(d);
        d.setParent(this);
    }
}

class D {

    private String d1;

    @JsonBackReference
    private C parent;

    public C getParent() {
        return parent;
    }

    public void setParent(C parent) {
        this.parent = parent;
    }

    public String getD1() {
        return d1;
    }

    public void setD1(String d1) {
        this.d1 = d1;
    }

}



