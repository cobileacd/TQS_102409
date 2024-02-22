package com.mycompany.app;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.function.Executable;

public class TqsStackTest
{
    public TqsStack<Integer> stack;

    @BeforeEach
    public void setup()
    {
        stack = new TqsStack<Integer>();
    }

    @Test
    public void isStackEmpty()
    {
        assertTrue( stack.isEmpty() );
    }

    @Test
    public void doesStackHaveSize0()
    {
        assertTrue( stack.size() == 0 );
    }

    //After n pushes to an empty stack, n > 0, the stack is not empty and its size is n
    @Test
    public void sizeAfterPush()
    {
        int n = 5;
        for (int i = 0; i < n; i++)
        {
            stack.push(i);
        }

        assertTrue( stack.size() == n );
    }

    // If one pushes x then pops, the value popped is x.
    @Test
    public void popAfterPush()
    {
        stack.push(5);
        int x = stack.pop();

        assertTrue( x == 5 );
    }

    // If one pushes x then peeks, the value returned is x, but the size stays the same
    @Test
    public void sameSizeAfterPeek()
    {
        assertTrue( stack.isEmpty() );
        stack.push(1);
        assertTrue( stack.size() == 1 );

        int n = stack.peek();

        assertTrue( n == 1);
        assertTrue( stack.size() == 1 );
    }

    // If the size is n, then after n pops, the stack is empty and has a size 0
    @Test
    public void sizeAfterPops()
    {
      stack.push(0);
      stack.push(1);
      stack.push(2);
      stack.pop();
      stack.pop();
      stack.pop();
      assertTrue( stack.isEmpty() );
      assertTrue( stack.size() == 0 );
    }

    // Popping from an empty stack does throw a NoSuchElementException
    @Test
    public void popThrowsException()
    {
        assertThrows(NoSuchElementException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
            stack.pop(); 
            }
            });
    }

    // Peeking into an empty stack does throw a NoSuchElementException
    @Test
    public void peekThrowsException()
    {
        assertThrows(NoSuchElementException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
            stack.pop(); 
            }
            });
    }

}
