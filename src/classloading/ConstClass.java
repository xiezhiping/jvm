package classloading;
/**
 * �����ڱ���׶λ���������ĳ������У������ϲ�û�����õ����峣�����࣬��˲��ᴥ�����峣����ĳ�ʼ��
 * @author л֮ƽ
 *
 */
public class ConstClass {
	static {
		System.out.println("ConstClass init!");
	}
	public static final String HELLOWORLD = "hello world";
}
