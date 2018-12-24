package classloading;
/**
 * 常量在编译阶段会存入调用类的常量池中，本质上并没有引用到定义常量的类，因此不会触发定义常量类的初始化
 * @author 谢之平
 *
 */
public class ConstClass {
	static {
		System.out.println("ConstClass init!");
	}
	public static final String HELLOWORLD = "hello world";
}
