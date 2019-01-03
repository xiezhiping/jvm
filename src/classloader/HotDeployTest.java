package classloader;
/**
 * �Ȳ���Ĳ�����
 * ����������˵����
 * �Ȳ����������ͬһ��class�ļ���ͬ������������ڴ洴����������ͬ��class����.
 * ����JVM�ڼ�����֮ǰ������������Ƿ��Ѽ��ع�(����loadClass()�����е���findLoadedClass()����)��
 * ��������ع�����ֱ�Ӵӻ����ȡ���������¼��ء�ע��ͬһ�����������ʵ����ͬһ��class�ļ�ֻ�ܱ�������һ�Σ���μ��ؽ�����.
 * �������ʵ�ֵ��Ȳ��������ͬһ��class�ļ����Ը��ݲ�ͬ����������ظ����أ���ʵ���Ȳ���Ҳ��Ϊͬһ��class�ļ�д������ͬ���������
 * @author XZP
 *
 */
public class HotDeployTest {

	public static void main(String[] args) {
		String rootDir = "F:\\java_workspace\\jvm\\src\\classloader";
		// �����Զ�����ļ�����:������ͬ�Ķ���
		FileClassLoader loader1 = new FileClassLoader(rootDir);
		FileClassLoader loader2 = new FileClassLoader(rootDir);
		try {
			// ͨ������loadClass()����ָ����class�ļ�
			Class<?> object1 = loader1.loadClass("classloader.FileResObj");
			Class<?> object2 = loader2.loadClass("classloader.FileResObj");
			System.out.println("obj1 by loadClass:" + object1.hashCode()); // ���������hashCode��ȣ�˵����ͬһ����
			System.out.println("obj2 by loadClass:" + object2.hashCode());
			// ͨ������findClass()�����ƹ���⣬������ͬ��class����
			Class<?> object3 = loader1.findClass("FileResObj"); // ���������hashCode�����˵���ǲ�ͬ����
			Class<?> object4 = loader2.findClass("FileResObj");
			System.out.println("obj3 by findClass:" + object3.hashCode());
			System.out.println("obj4 by findClass:" + object4.hashCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
