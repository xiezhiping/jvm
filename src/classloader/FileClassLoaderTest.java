package classloader;

public class FileClassLoaderTest {
	/**
	 * ���Ե�main����
	 * @param args
	 */
	public static void main(String[] args) {
		String rootDir = "/classloader/";
		// �����Զ�����ļ�����
		FileClassLoader loader = new FileClassLoader(rootDir);
		try {
			// ����ָ����class�ļ�
			Class<?> object = loader.loadClass("classloader.FileResObj");
			System.out.println(object.newInstance().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
