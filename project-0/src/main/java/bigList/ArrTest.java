package bigList;

public class ArrTest {
    public static void main(String[] args){
        myArrList<Integer> i = new myArrList<>();

        i.add(0);
        i.add(1);
        i.add(2);
        i.add(3);
        i.add(4,3);
        i.print();
        System.out.println("\n" + i.count);
        i.remove(2);
        i.print();
    }
}
