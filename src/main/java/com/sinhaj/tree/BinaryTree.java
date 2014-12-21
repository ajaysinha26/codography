package com.sinhaj.tree;

import java.util.*;

/**
 * Created by ajaysinha on 1/28/14.
 */
public class BinaryTree<T> {
    private T value;
    private BinaryTree<T> left;
    private BinaryTree<T> right;

    public BinaryTree(T value) {
        this.value = value;
    }

    public void construct(int[] treeNodes){
        BinaryTree<Integer> root = null;
        if(treeNodes[0] != -1) {
            root = new BinaryTree<Integer>(treeNodes[0]);
        }
        Queue<BinaryTree> queue = new LinkedList<BinaryTree>();
        queue.add(root);

        for(int i = 1; i < treeNodes.length;) {
            int noOfParents = i;
            for(int j = 1; j <= noOfParents; j++) {
                System.out.println(i + ":" + j);
                BinaryTree<Integer> node = queue.poll();
                if(node != null) {
                    if(i < treeNodes.length && treeNodes[i] != -1) {
                        BinaryTree leftChild = new BinaryTree(treeNodes[i]);
                        node.left = leftChild;
                        queue.add(leftChild);
                    }
                    if (i < treeNodes.length && treeNodes[i+1] != -1) {
                        BinaryTree rightChild = new BinaryTree(treeNodes[i + 1]);
                        node.right = rightChild;
                        queue.add(rightChild);

                    }

                }
                i = i + 2;
            }
        }
    }

    public List<T> inOrder() {
        List<T> inOrderTraversalElements = new ArrayList<T>();
        traverseInOrder(inOrderTraversalElements);
        return inOrderTraversalElements;
    }

    private void traverseInOrder(List<T> inOrderTraversalElements) {
        if(this == null) {
            return;
        }
        if(this.left != null) {
            this.left.traverseInOrder(inOrderTraversalElements);
        }
        inOrderTraversalElements.add(this.value);
        if(this.right != null) {
            this.right.traverseInOrder(inOrderTraversalElements);
        }
    }

    public List<T> preOrder() {
        List<T> preOrderTraversalElements = new ArrayList<T>();
        traversePreOrder(preOrderTraversalElements);
        return preOrderTraversalElements;
    }

    private void traversePreOrder(List<T> preOrderTraversalElements) {
        if(this == null) {
            return;
        }
        preOrderTraversalElements.add(this.value);
        if(this.left != null) {
            this.left.traversePreOrder(preOrderTraversalElements);
        }
        if(this.right != null) {
            this.right.traversePreOrder(preOrderTraversalElements);
        }
    }

    public List<T> postOrder() {
        List<T> postOrderTraversalElements = new ArrayList<T>();
        traversePostOrder(postOrderTraversalElements);
        return postOrderTraversalElements;
    }

    private void traversePostOrder(List<T> postOrderTraversalElements) {
        if(this == null) {
            return;
        }
        if(this.left != null) {
            this.left.traversePostOrder(postOrderTraversalElements);
        }
        if(this.right != null) {
            this.right.traversePostOrder(postOrderTraversalElements);
        }
        postOrderTraversalElements.add(this.value);
    }

    public List<T> BFS() {
        List<T> bfsTraversalElements = new ArrayList<T>();
        Queue<BinaryTree<T>> nodes = new LinkedList<BinaryTree<T>>();
        nodes.add(this);
        while (!nodes.isEmpty()) {
            BinaryTree<T> node = nodes.poll();
            if(node != null) {
                bfsTraversalElements.add(node.value);
                nodes.add(node.left);
                nodes.add(node.right);
            }
        }

        return bfsTraversalElements;
    }

    public List<T> DFS() {
        List<T> dfsTraversalElements = new ArrayList<T>();
        Stack<BinaryTree<T>> nodes = new Stack<BinaryTree<T>>();
        nodes.push(this);
        while (!nodes.isEmpty()) {
            BinaryTree<T> node = nodes.pop();
            if(node != null) {
                nodes.push(node);
                BinaryTree<T> leftNode = node.left;
                while (leftNode != null) {
                    nodes.push(leftNode);
                    leftNode = leftNode.left;
                }
                BinaryTree<T> leftMostNode = nodes.pop();
                if(leftMostNode != node.left || leftMostNode.right == null) {
                    dfsTraversalElements.add(leftMostNode.value);
                }
                BinaryTree<T> rightNode = node.right;
                while (rightNode != null) {
                    nodes.push(rightNode);
                    rightNode = rightNode.right;
                }
                BinaryTree<T> rightMostNode = nodes.pop();
                if(rightMostNode != node.right || rightMostNode.left == null) {
                    dfsTraversalElements.add(rightMostNode.value);
                }
            }
        }
        dfsTraversalElements.add(nodes.pop().value);

        return dfsTraversalElements;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public BinaryTree<T> getLeft() {
        return left;
    }

    public void setLeft(BinaryTree<T> left) {
        this.left = left;
    }

    public BinaryTree<T> getRight() {
        return right;
    }

    public void setRight(BinaryTree<T> right) {
        this.right = right;
    }
}
