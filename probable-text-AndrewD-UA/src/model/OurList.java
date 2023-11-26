package model;

/**
 * A really small List ADT
 * 
 * @param <Type> The type of element stored in this list
 * 
 * @author mercer
 */

public interface OurList<Type> {
  
  // Return how many elements are in the list.
  public int size();

  // Return the value of the element stored at the given index.
  // Precondition: 0 <= getIndex < size()
  public Type get(int getIndex);
  
  // Add element at the front of the list
  public void addFront(Type element);
 
}