package org.rhino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author u.sharma
 *
 */
public class Person {

    private static final List<Person> staticPerson = new ArrayList<Person>() {
        private static final long serialVersionUID = 8751914654566394666L;

        {
            add(new Person("Name 1", "Spouse 1", false, 1));
            add(new Person("Name 2", "Spouse 2", true, 3));
            add(new Person("Name 3", "Spouse 3", true, 0));
        }
    };

    private String name;
    private boolean married;
    private String spouse;
    private List<String> children;

    public Person() {
        this.name = "";
        this.married = false;
        this.spouse = "";
        children = Collections.emptyList();
    }

    public Person(String name, String spouse, boolean isMarried, int nChildren) {
        super();
        this.name = name;
        this.spouse = spouse;
        this.married = isMarried;
        children = new ArrayList<String>();
        for (int z = 0; z < nChildren; z++) {
            children.add("Children " + z);
        }
    }

    //New constructor with child name. Child names are comma saperated Ex. "Anna,Berta,Clara"
    public Person(String name, String spouse, boolean isMarried, int nChildren, String childName) {
        super();
        this.name = name;
        this.spouse = spouse;
        this.married = isMarried;
        children = new ArrayList<String>();
        children = Arrays.asList(childName.split("\\s*,\\s*"));
    }

    public static Person lookup(String id) {
        if (id != null) {
            int _id = Integer.valueOf(id);
            if (_id <= staticPerson.size()) {
                return staticPerson.get(_id - 1);
            } else if(_id==4)  {
              return new Person("Maria","Pere",true,3,"Anna,Berta,Clara");
            } else {
                return new Person("Empty Name", "Empty spouse", false, 0);
            }
        } else {
            return new Person("Empty Name", "Empty spouse", false, 0);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpouse() {
        return this.spouse;
    }

    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    public List<String> getChildren() {
        return children;
    }

    public void setChildren(List<String> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", married=" + married + ", spouse="
                + spouse + ", children=" + children + "]";
    }
}
