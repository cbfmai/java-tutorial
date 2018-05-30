package com.imdnd.comparator;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Adam DENG
 * @Date 2018/5/30 14:32
 */
public class SortStudent {


    /**
     *
     * jdk 8 以下的排序
     *
     * @param studentList
     * @return
     */
    private List<Student> sortByIdUnderJdk8(List<Student> studentList) {

        if (CollectionUtils.isEmpty(studentList)) {
            return null;
        }

        Collections.sort(studentList, new IdComparator());
        return studentList;
    }

    /**
     *
     * With Comparable
     *
     * @param studentList
     * @return
     */
    private List<Student> sortByIdWithComparable(List<Student> studentList) {

        if (CollectionUtils.isEmpty(studentList)) {
            return null;
        }

        Collections.sort(studentList);
        return studentList;
    }

    /**
     *
     * With JDK8
     *
     * @param studentList
     * @return
     */
    private List<Student> sortByIdWithJdk8(List<Student> studentList) {

        if (CollectionUtils.isEmpty(studentList)) {
            return null;
        }
        studentList.sort(Comparator.comparing(Student::getId));
        return studentList;
    }


    /**
     *
     * 结果：
     *
     * [{"id":1,"name":"Adam DENG"},{"id":21,"name":"John Liao"},{"id":22,"name":"Jack Ma"},{"id":32,"name":"Json liu"}]
     * [{"id":1,"name":"Adam DENG"},{"id":21,"name":"John Liao"},{"id":22,"name":"Jack Ma"},{"id":32,"name":"Json liu"}]
     * [{"id":1,"name":"Adam DENG"},{"id":21,"name":"John Liao"},{"id":22,"name":"Jack Ma"},{"id":32,"name":"Json liu"}]
     * @param args
     */
    public static void main(String[] args) {

        List<Student> studentList = new ArrayList<>();
        Student student1 = new Student(1, "Adam DENG");
        Student student4 = new Student(32, "Json liu");
        Student student2 = new Student(21, "John Liao");
        Student student3 = new Student(22, "Jack Ma");

        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);

        SortStudent sortStudent = new SortStudent();

        System.out.println(JSON.toJSONString(sortStudent.sortByIdUnderJdk8(studentList)));
        System.out.println(JSON.toJSONString(sortStudent.sortByIdWithComparable(studentList)));
        System.out.println(JSON.toJSONString(sortStudent.sortByIdWithJdk8(studentList)));
    }

}
