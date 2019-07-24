package android.js.com.myapplication.feature.utils;

public class MathUtils {
	
	public static void test(String[] arg) {
		System.out.println(upperInteger());
		System.out.println(upperInteger(2,5));
	}
	
	public static Integer upperInteger(){
		double a=1;
		double b=5;
		double x= Math.ceil(a/b);
		return (int)x;
	}

	public static Integer upperInteger(double total,double size){
		double x= Math.ceil(total/size);
		return (int)x;
	}

	public static int mod(int i,int m){
		return i%m;
	}

	public static void main(String[] args) {
		System.out.println(mod(0,4));
		System.out.println(mod(1,4));
		System.out.println(mod(2,4));
		System.out.println(mod(3,4));
		System.out.println(mod(5,4));
		System.out.println(mod(6,4));
		System.out.println(mod(7,4));
		System.out.println(mod(8,4));
	}


}
