package classloader;

public class FileClassLoaderTest {
	/**
	 * 测试的main方法
	 * @param args
	 */
	public static void main(String[] args) {
		String rootDir = "/classloader/";
		// 创建自定义类的加载器
		FileClassLoader loader = new FileClassLoader(rootDir);
		try {
			// 加载指定的class文件
			Class<?> object = loader.loadClass("classloader.FileResObj");
			System.out.println(object.newInstance().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
