package com.imdnd.algorithm.transformer;

import java.util.List;

/**
 * @author Adam DENG
 * @Date 2018/6/8 9:19
 */
public class Node {

    private Long id;

    private String name;

    private Long parentId;

    List<Node> subNodeList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Node> getSubNodeList() {
        return subNodeList;
    }

    public void setSubNodeList(List<Node> subNodeList) {
        this.subNodeList = subNodeList;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                '}';
    }
}
