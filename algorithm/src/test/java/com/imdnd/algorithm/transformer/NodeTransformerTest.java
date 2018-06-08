package com.imdnd.algorithm.transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adam DENG
 * @Date 2018/6/8 9:39
 */
public class NodeTransformerTest {

    public static void main(String[] args) {
        NodeTransformerTest transformerTest = new NodeTransformerTest();
        transformerTest.treeToList();
        transformerTest.listToTree();
    }

    private void listToTree() {
        List<Node> nodeList = new ArrayList<>();

        Node node = new Node();
        node.setId(1L);
        node.setName("parent");
        node.setParentId(0L);

        Node subNode1 = new Node();
        subNode1.setId(2L);
        subNode1.setParentId(1L);
        subNode1.setName("subnode1");

        Node subNode2 = new Node();
        subNode2.setId(2L);
        subNode2.setParentId(1L);
        subNode2.setName("subnode2");

        nodeList.add(node);
        nodeList.add(subNode1);
        nodeList.add(subNode2);

        List<Node> nodes = NodeUtils.traverse(0L, nodeList);
        System.out.println(nodes.toString());
    }


    private void treeToList() {

        Node node = new Node();
        node.setId(1L);
        node.setName("parent");
        node.setParentId(0L);

        List<Node> subNodeList = new ArrayList<>();

        Node subNode1 = new Node();
        subNode1.setId(2L);
        subNode1.setParentId(1L);
        subNode1.setName("subnode1");

        Node subNode2 = new Node();
        subNode2.setId(2L);
        subNode2.setParentId(1L);
        subNode2.setName("subnode2");

        subNodeList.add(subNode1);
        subNodeList.add(subNode2);
        node.setSubNodeList(subNodeList);

        List<Node> nodes = NodeUtils.flatten(node);
        System.out.println(nodes);

    }

}
