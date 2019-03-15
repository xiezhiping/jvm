package optimize;

public class BaseTest {
	public static void main(String[] args) {
		Integer a = 1;
		Integer b = 2;
		Integer c = 3;
		Integer d = 3;
		Integer e = 321;
		Integer f = 321;
		Long g = 3L;
		System.out.println(c == d);
		System.out.println(e == f);
		System.out.println(c == (a + b));
		System.out.println(c.equals(a+b));
		System.out.println(g == (a+b));  // 包装类的“==”运算在不遇到算术运算的情况下不会自动拆箱
		System.out.println(g.equals(a+b)); // 以及他们的equals方法不处理数据转型的关系
		
	}
}
