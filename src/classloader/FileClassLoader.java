package classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 从特定路径的文件获取类的File类加载器
 * @author XZP
 *
 */
public class FileClassLoader extends ClassLoader {
	private String rootDir;
	public FileClassLoader(String rootDir) {
		this.rootDir = rootDir;
	}
	/**
	 * 重写父类的findClass方法
	 * @param name 文件名
	 * @return 找到类的字节码就用defineClass返回对应的对象
	 * @throws ClassNotFoundException
	 */
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		// 获取类的class文件字节数组
		byte[] classData = getClassDataFromFile(name);
		if (classData == null) {
			throw new ClassNotFoundException();
		} else {
			// 直接生成class对象
			return defineClass(name, classData, 0, classData.length);
		}
	}
	/**
	 * 获取class文件并转换为字节码流的逻辑
	 * @param name 文件名
	 * @return
	 */
	private byte[] getClassDataFromFile(String name) {
		// 读取类文件的字节
		String path = name2Path(name);
		System.out.println("path:" + path);
		try {
			InputStream ins = new FileInputStream(path);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int bufferSize = 4096;
			byte[] buffer = new byte[bufferSize];
			int bytesNumRead = 0;
			// 读取文件字节码
			while ((bytesNumRead = ins.read(buffer)) != -1) {
				baos.write(buffer, 0, bytesNumRead);
			}
			return baos.toByteArray();
		} catch(IOException io) {
			io.printStackTrace();
		}
		return null;
	}
	/**
	 * 通过文件名获取class文件的完全路径
	 * @param className 文件名
	 * @return
	 */
	private String name2Path(String className) {
		return rootDir +  File.separatorChar + className.replace('.', File.separatorChar) + ".class";
	}
}
