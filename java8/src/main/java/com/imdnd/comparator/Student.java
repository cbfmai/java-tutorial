package com.imdnd.comparator;


/**
 * @author Adam DENG
 * @Date 2018/5/30 14:31
 */
public class Student implements Comparable<Student>{

    private Integer id;

    private String name;

    public Student(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Student o) {
        return this.getId() - o.getId();
    }
}
