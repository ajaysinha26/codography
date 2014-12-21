package com.sinhaj.tree;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created by ajaysinha on 1/28/14.
 */
public class BinaryTreeTest {

    BinaryTree<Integer> root;

    @Before
    public void setUp() throws Exception {
        root = new BinaryTree<Integer>(1);
        root.setLeft(new BinaryTree<Integer>(2));
        root.getLeft().setRight(new BinaryTree<Integer>(4));
        root.getLeft().getRight().setRight(new BinaryTree<Integer>(5));
        root.getLeft().getRight().getRight().setLeft(new BinaryTree<Integer>(6));
        root.getLeft().getRight().getRight().setRight(new BinaryTree<Integer>(7));
        root.setRight(new BinaryTree<Integer>(8));
        root.getRight().setLeft(new BinaryTree<Integer>(9));
        root.getRight().getLeft().setLeft(new BinaryTree<Integer>(10));
        root.getRight().getLeft().setRight(new BinaryTree<Integer>(11));
    }

    @Test
    public void testShouldTraverseTreeInOrder() throws Exception {
        List<Integer> inOrderTraversalExpectedValues = Arrays.asList(new Integer[]{2, 4, 6, 5, 7, 1, 10, 9, 11, 8});
        assertEquals(inOrderTraversalExpectedValues, root.inOrder());
    }

    @Test
    public void testShouldTraverseTreePreOrder() throws Exception {
        List<Integer> inOrderTraversalExpectedValues = Arrays.asList(new Integer[]{1, 2, 4, 5, 6, 7, 8, 9, 10, 11});
        assertEquals(inOrderTraversalExpectedValues, root.preOrder());
    }

    @Test
    public void testShouldTraverseTreePostOrder() throws Exception {
        List<Integer> inOrderTraversalExpectedValues = Arrays.asList(new Integer[]{6, 7, 5, 4, 2, 10, 11, 9, 8, 1});
        assertEquals(inOrderTraversalExpectedValues, root.postOrder());
    }

    @Test
    public void testShouldTraverseTreeBFS() throws Exception {
        List<Integer> inOrderTraversalExpectedValues = Arrays.asList(new Integer[]{1 ,2 , 8, 4, 9, 5, 10, 11, 6, 7});
        assertEquals(inOrderTraversalExpectedValues, root.BFS());
    }
}
