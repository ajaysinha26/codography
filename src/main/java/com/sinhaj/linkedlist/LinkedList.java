package com.sinhaj.linkedlist;

/**
 * Created with IntelliJ IDEA.
 * User: ajaysinha
 * Date: 10/28/13
 * Time: 1:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class LinkedList {
    private Node head;
    private int length;

    public LinkedList() {
        head = null;
        length = 0;
    }

    public void add(int data) {
        Node node = new Node();
        node.data = data;
        node.next = null;
        if(head == null) {
            head = node;
            length = 1;
        }
        else {
            Node tempNode = head;
            while (tempNode.next != null) {
                tempNode = tempNode.next;
                length++;
            }
            tempNode.next = node;
            length++;
        }
    }

    public int get(int index) {
        if(head == null) {
            throw new RuntimeException("Empty LinkedList");
        }
        Node tempNode = head;
        int counter = 0;
        while (counter < index) {
            tempNode = head.next;
        }
        return tempNode.data;
    }

    public int length() {
        return length;
    }
}

class Node {
    int data;
    Node next;
}
