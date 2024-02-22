package com.mycompany.app;

import java.util.NoSuchElementException;
import java.util.LinkedList;

public class TqsStack<T> {
    private LinkedList<T> collection;

    public TqsStack()
    {
        this.collection = new LinkedList<T>();
    }

    public T pop()
    {
        if(isEmpty())
            throw new NoSuchElementException();

        return collection.pollLast();
    }

    public T peek()
    {
        if(isEmpty())
            throw new NoSuchElementException();

        return collection.getLast();
    }

    public void push(T item)
    {
        collection.add(item);
    }

    public int size()
    {
        return collection.size();
    }

    public boolean isEmpty()
    {
        return collection.size() == 0;
    }
}
