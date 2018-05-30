package com.imdnd.comparator;

import java.util.Comparator;

/**
 * @author Adam DENG
 * @Date 2018/5/30 14:33
 */
public class IdComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return o1.getId() - o2.getId();
    }
}
