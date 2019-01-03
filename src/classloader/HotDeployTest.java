package classloader;
/**
 * 热部署的测试类
 * 作如下特殊说明：
 * 热部署就是利用同一个class文件不同的类加载器在内存创建出两个不同的class对象.
 * 由于JVM在加载类之前会检测请求的类是否已加载过(即在loadClass()方法中调用findLoadedClass()方法)，
 * 如果被加载过，则直接从缓存获取，不会重新加载。注意同一个类加载器的实例和同一个class文件只能被加载器一次，多次加载将报错.
 * 因此我们实现的热部署必须让同一个class文件可以根据不同的类加载器重复加载，以实现热部署：也即为同一个class文件写两个不同的类加载器
 * @author XZP
 *
 */
public class HotDeployTest {

	public static void main(String[] args) {
		String rootDir = "F:\\java_workspace\\jvm\\src\\classloader";
		// 创建自定义类的加载器:两个不同的对象
		FileClassLoader loader1 = new FileClassLoader(rootDir);
		FileClassLoader loader2 = new FileClassLoader(rootDir);
		try {
			// 通过调用loadClass()加载指定的class文件
			Class<?> object1 = loader1.loadClass("classloader.FileResObj");
			Class<?> object2 = loader2.loadClass("classloader.FileResObj");
			System.out.println("obj1 by loadClass:" + object1.hashCode()); // 输出的两者hashCode相等，说明是同一对象
			System.out.println("obj2 by loadClass:" + object2.hashCode());
			// 通过调用findClass()方法绕过检测，创建不同的class对象
			Class<?> object3 = loader1.findClass("FileResObj"); // 输出的两者hashCode不相等说明是不同对象
			Class<?> object4 = loader2.findClass("FileResObj");
			System.out.println("obj3 by findClass:" + object3.hashCode());
			System.out.println("obj4 by findClass:" + object4.hashCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
