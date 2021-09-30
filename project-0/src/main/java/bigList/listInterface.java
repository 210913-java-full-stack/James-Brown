package bigList;

public interface listInterface<T> {
    //returns number of elements in list
    int getCount();

    //returns size of current arrList
    int getSize();

    //adds an element to list
    void add(T t);

    //add element at a specified point, will shift other items or increase size to make room
    void add(T t, int index);

    //shifts array elements to fill in void at specified index
    void shiftArray(int i);

    //returns element at index
    T get(int index);

    //remove element at specified index, closes gap afterwards
    void remove(int index);

    //remove all elements
    void clear();

    //returns t/f if value is present
    boolean contains(T t);

    //returns first found index of specified value
    int getIndex(T t);

    //array resize
    void updateSize();

    void checkSize();

    void print();
}
