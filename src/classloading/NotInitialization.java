package classloading;
/**
 * ����ʹ�����ֶ�
 * ͨ���������ø���ľ�̬�ֶΣ����ᵼ�������ʼ��
 * @author л֮ƽ
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
		System.out.println(ConstClass.HELLOWORLD); // �����ྲ̬�ֶδ��ڳ����أ���˲�����������ĳ�ʼ��
	}
}
