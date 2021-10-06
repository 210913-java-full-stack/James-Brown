package bigList;

/**
 * Generic ArrayList
 * @param <T> can be any object/primitive type
 */
public class myArrList<T> implements listInterface<T> {
    int size;
    int count;
    Object[] arr;

    /**
     * Initializes the arrayList at a size of 10
     */
    public myArrList(){
        size = 10;
        count = 0;
        arr = new Object[size];
    }

    /**
     * @return number of objects in arrayList
     */
    public int getCount(){
        return count;
    }

    /**
     * @return maximum size of arrayList
     */
    public int getSize(){
        return size;
    }

    /**
     * adds an object to the arrayList
     * @param t generic object to be added
     */
    public void add(T t){
        checkSize();
        arr[count] = t;
        count++;
    }

    /**
     * doubles the size of the array whenever necessary;
     */
    public void updateSize(){
        size *= 2;
        Object[] oldArr = arr.clone();
        arr = new Object[size];

        for (int i = 0; i < count; i++){
            arr[i] = oldArr[i];
        }

    }

    /**
     * checks if arrayList needs to be upsized.
     */
    public void checkSize(){
        if (count == size) {
            updateSize();
        }
    }

    /**
     * add in generic element at a specific point in arrayList
     * will shift other objects to make room at specified index
     * @param t generic object to be added
     * @param index index where the object should be inserted
     */
    public void add(T t, int index){
        checkSize();
        T hold;
        T shift;

        if (index == count) {add(t);}
        else if (get(index) == null){
            add(t, count);
        }
        else {
            hold = (T) arr[index];
            arr[index] = t;
            count++;

            for(int i = index + 1; i < count; i++){
                shift = (T) arr[i];
                arr[i] = hold;
                hold = shift;
            }
        }
    }

    /**
     * retrieves an object at the specified index
     * @param index
     * @return
     */
    public T get(int index){
        return (T) arr[index];
    }

    /**
     * Used to shift object to the front of the arrayList after a removal
     * @param index
     */
    public void shiftArray(int index){
        T shift;
        for (int i = index + 1; i <= count; i++){
            shift = (T) arr[i];
            arr[i - 1] = shift;
        }
        arr[count] = null;
    }

    /**
     * removes object from arrayList and
     * also calls shiftArray to fill in gaps
     * @param index
     */
    public void remove(int index){
        arr[index] = null;
        count--;
        shiftArray(index);
    }

    /**
     * dereferences the arrayList for a fresh slate
     */
    public void clear(){
        count = 0;
        arr = new Object[size];
    }

    /**
     * does a light check on the objects in the arrayList vs t
     * @param t generic object
     * @return true if t is contained in the arrList. False if not
     */
    public boolean contains(T t){
        if(count > 0){
            for (int i = 0; i < count; i++){
                if (arr[i].equals(t)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * gives index to user based on object parameter.
     * @param t generic
     * @return index of object t in arrayList
     */
    public int getIndex(T t) {
        int dex = -1;
        if (contains(t)) {
            for (int i = 0; i < count; i++) {
                if (arr[i].equals(t)) {
                    dex = i;
                    return dex;
                }
            }
        }
        return dex;
    }

    /**
     * prints objects (most likely will need further implementation)
     */
    public void print(){
        for (int i = 0; i < count; i++){
            System.out.print(arr[i] + " ");
        }
    }

}
