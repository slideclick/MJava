//import ds.util.Arrays;
import weiss.util.Arrays;
class MyClass{
private int i ;
public MyClass(int ii){
    this.i = ii;
}

}

public class helloArray {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
        
        MyClass a[] = new MyClass[10];
        for (int j  = 0; j< 10; j++){
            a[j] = new MyClass(j);
        }
        
        System.out.println(Arrays.toString(a));
        
	}

}
