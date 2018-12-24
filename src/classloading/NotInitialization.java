package classloading;
/**
 * 被动使用类字段
 * 通过子类引用父类的静态字段，不会导致子类初始化
 * @author 谢之平
 *
 */
class SuperClass {
	static {
		System.out.println("SuperClass init!");
	}
	public static int value = 123;
}
class SubClass extends SuperClass {
	static {
		System.out.println("SubClass init!");
	}
}
public class NotInitialization {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(SubClass.value);
		System.out.println(ConstClass.HELLOWORLD); // 常量类静态字段存在常量池，因此不会引起常量类的初始化
	}
}
