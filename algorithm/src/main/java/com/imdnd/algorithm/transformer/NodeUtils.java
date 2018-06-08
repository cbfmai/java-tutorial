package com.imdnd.algorithm.transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adam DENG
 * @Date 2018/6/8 9:19
 */
public class NodeUtils {

    /**
     * Tree object to List
     *
     * @param parentNode
     * @return
     */
    public static List<Node> flatten(Node parentNode) {
        if (parentNode == null) {
            return null;
        }
        List<Node> flatList = new ArrayList<>();
        flatten(parentNode, flatList);
        return flatList;
    }

    private static void flatten(Node parent, List<Node> nodeList) {
        if (parent != null) {
            nodeList.add(parent);
            List<Node> subNodeList = parent.getSubNodeList();
            if (subNodeList != null && subNodeList.size() > 0) {
                for (Node node : subNodeList) {
                    flatten(node, nodeList);
                }
            }
        }
    }

    /**
     * List object to Tree object
     *
     * @param nodeList
     * @return
     */
    public static List<Node> traverse(Long parentId, List<Node> nodeList) {
        if (parentId == null || nodeList == null || nodeList.size() == 0) {
            return null;
        }

        List<Node> nodes = new ArrayList<>();
        for (Node node : nodeList) {
            if (node.getParentId().compareTo(parentId) == 0) {
                node.setSubNodeList(traverse(node.getId(), nodeList));
                nodes.add(node);
            }
        }

        return nodes;
    }
}
