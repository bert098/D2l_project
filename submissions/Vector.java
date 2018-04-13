/*
 * This is a vector class similar to the java library 
 */
public class Vector  {
	//The array holding the elements
    protected Object[] items;
    //the number of elements in the array
    protected int arraySize;
    //the maximum size of the array
    protected int maxCap;

    //creates the array with a specified size
    public Vector (int initialCapacity) {
        
        this.items = new Object[initialCapacity];
        this.arraySize = 0;
        this.maxCap = initialCapacity;
    }
    /*
     * If the vector size is not specified then it will create 
     * one with an initialize size of ten
     */
    public Vector() {
        this(10);
    }
    /*
     * this appends an element to end of the vector by first checking if the array is full
     * and then either increasing the array size or simply putting an element on the end of the array
     */
    public void append(Object element) {
        int newArraySize = arraySize + 1;
        if(maxCap == newArraySize) {
            items = increaseCap(newArraySize);
            items[arraySize] = element;
            arraySize += 1;
        } else {
            items[arraySize] = element;
            arraySize +=1;
        }
    }
  //clears the contents fo the array
    public void clear() {
        for(int i = 0; i < arraySize; i++) {
            items[i] = null;
            arraySize = 0;
        }
    }
    //returns the element at a specified index
    public Object elementAt(int index) {
        if(arraySize >= index) {
            return items[index];
        } else {
            Object temp = null;
            System.out.println("No index of " + index);
            return temp; 
        }
    }
    //returns the index of a specified object
    public Object indexOf(Object element) {
        Object index = "No value found";
        for(int i = 0; i < arraySize; i++) {
            if(element == items[i]) {
                index = i;
                break;        
            } 
        }
        return index;
    }
    //checks if the vector is empty
    public boolean isEmpty() {
        if(arraySize == 0) {
            return true;
        }
        return false;
    }
  //returns the size of the vector
    public int size() {
        return arraySize;
    }
  /*
   * This is used if the program tries to append an element to the end of array but
   * the array is full, so if that is the case is doubles the size of the vector
   */
    public Object[] increaseCap(int minCap) {
        Object[] arr1 = new Object[minCap * 2];
        for(int i = 0; i < minCap; i++) {
            arr1[i] = items[i];
        }
        maxCap = maxCap * 2;
        return arr1;
    }
  //checks if there is an element at an index
    public boolean checkIndex(int index) {
        boolean check = false;
        if(index < arraySize) {
            check = true;
        }
        return check;
    }
    //removes an item at a specifies index
    public void removeAt(int index) {
        if(true == this.checkIndex(index)) {
            Object[] temp = new Object[this.arraySize - 1];
            for(int j = 0; j < index; j++) {
                temp[j] = items[j];
            }
            for(int j = index + 1; j < arraySize; j++) {
                temp[j-1] = items[j];
            }
            items = temp;
            arraySize = arraySize - 1;
        }
    }
    //inserts an item at a specifed index
    public void insertAt(int index, Object element) {
        if (this.checkIndex(index) == true) {
            Object[] temp = new Object[arraySize];
            for(int i = index; i < arraySize; i++) {
                temp[i+1] = items[i];
            }
             items[index] = element;
            for (int i = index + 1; i < arraySize; i++) {
                items[i] = temp[i - 1];
            }
            arraySize = arraySize - 1;
        }
    }
    //searches for an object in the array and removes it 
    public void remove(Object element) {
        for(int i = 0; i < items.length; i++) {
            if(items[i] == element) {
                this.removeAt(i);
            }
        }
    }
    
  
}
