package binaryheap;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * This class implements a binary heap data structure by extending the
 * ArrayList class.
 * @author tcolburn
 */
public class BinaryHeap<E> extends ArrayList<E> {

    /**
     * Creates an empty binary heap with a given capacity and comparator.
     * @param capacity The initial size of the underlying ArrayList object.
     * @param comp A comparator object for comparing keys of binary heap elements.
     */
    public BinaryHeap(int capacity, Comparator<E> comp) {
        super(capacity+1);
        init();
        this.comp = comp;
    }

    /**
     * Initializes the underlying ArrayList object for use as a binary heap.
     * A null object is added to location 0, which is not used by the heap.
     */
    private void init() {
        add(0, null);
    }

    /**
     * Clears this binary heap by clearing and initializing the underlying
     * ArrayList object.
     */
    public void clear() {
        super.clear();
        init();
    }

    /**
     * Returns the current size of this binary heap.  Since the first location 
     * (index 0) of the underlying ArrayList object is not used, the size of the 
     * binary heap is one less than the size of the ArrayList object.
     * @return The binary heap's current size. 
     */
    public int size() {
        return super.size()-1;
    }

    /**
     * Returns true if this binary heap is empty, that is, its size is zero.
     * @return Whether this binary heap is empty.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Adds a new element to this binary heap.  At the end of the add,
     * the heap has one more element and the heap property is maintained.
     * @param element The element to add
     * @return true.  In accordance with the Collection interface, returns
     * true since duplicate elements are allowed.
     */
    public boolean add(E element) {
        
        super.add(element);
        int i = size();
        
        while(i > 1){
            try{
                if(comp.compare(element, super.get(i/2) ) < 0){
               
                E temp = super.get(i/2); 
                super.set(i,temp);
                super.set(i/2, element);
                i /= 2;
               }
                else i = 0;
            }catch(NullPointerException e){System.err.println("NullPointerException: "+ e.getMessage());}
            
        }
        return true;
    }

    /**
     * Removes an element from the root of this binary heap.  After removal,
     * the heap has one less element and the heap property is restored.
     * This method does not override any method in the ArrayList class 
     * (note absence of an index parameter).
     * @return The removed element
     */
    public E remove() {
        
        E temp = super.get(size() );
        E retVal = super.get(1);
        super.set(1, temp);
        int i = 1;
        int j = 0;
        super.remove(size());
        try{
        while(i < size()){
            //try{
            if(comp.compare(super.get(2 * i), super.get(2*i+1)) < 0)
            {
                j = 2 * i;
            }
            else j = 2 * i +1;
            
            if(comp.compare(super.get(i), super.get(j)) > 0)
            {
                E tempSwap = super.get(j);
                super.set(j, super.get(i));
                super.set(i, tempSwap);
                i = j;
            }
            else i = super.size(); 
        }
        }catch(IndexOutOfBoundsException e){/*System.err.println("IndexOutOfBoundsException: " + e.getMessage());*/}
        //super.remove(size());
        return retVal;
    }
    
    
    /**
     * A Comparator object used to compare binary heap elements during its
     * add and remove operations.
     */
    private Comparator<E> comp;

}
