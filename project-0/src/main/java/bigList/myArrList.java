package bigList;

public class myArrList<T> implements listInterface<T> {
    int size;
    int count;
    Object[] arr;

    //remember default empty values are null (since it is object)
    public myArrList(){
        size = 10;
        count = 0;
        arr = new Object[size];
    }

    public int getCount(){
        return count;
    }

    public int getSize(){
        return size;
    }

    public void add(T t){
        checkSize();
        arr[count] = t;
        count++;
    }

    public void updateSize(){
        size *= 2;
        Object[] oldArr = arr.clone();
        arr = new Object[size];

        for (int i = 0; i < count; i++){
            arr[i] = oldArr[i];
        }

    }

    public void checkSize(){
        if (count == size) {
            updateSize();
        }
    }

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

    public T get(int index){
        return (T) arr[index];
    }

    public void shiftArray(int index){
        T shift;
        for (int i = index + 1; i <= count; i++){
            shift = (T) arr[i];
            arr[i - 1] = shift;
        }
        arr[count] = null;
    }

    public void remove(int index){
        arr[index] = null;
        count--;
        shiftArray(index);
    }

    public void clear(){
        count = 0;
        arr = new Object[size];
    }

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

    //returns -1 if t is not present
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

    public void print(){
        for (int i = 0; i < count; i++){
            System.out.print(arr[i] + " ");
        }
    }

}
