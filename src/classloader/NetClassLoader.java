package classloader;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

public class NetClassLoader extends ClassLoader {
	private String url; // class文件所在的URL
	public NetClassLoader(String url) {
		this.url = url;
	}
	/**
	 * 重写父类的findClass方法
	 */
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] classData = getClassDataFromNet(name);
		if (classData == null) {
			throw new ClassNotFoundException();
		} else {
			return defineClass(name,classData, 0, classData.length);
		}
	}
	/**
	 * 从网络获取class文件，不包含解密
	 * @param name
	 * @return
	 */
	private byte[] getClassDataFromNet(String name) {
		String path = name2Path(name);
		try {
			URL url = new URL(path);
			InputStream ins = url.openStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int bufferSize = 4096;
			byte[] buffer = new byte[bufferSize];
			int hasRead = 0;
			while ((hasRead = ins.read(buffer)) != -1) {
				baos.write(buffer, 0, hasRead);
			}
			// 可能还涉及解密，在此省略，但是是非常重要的一个内容
			return baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据文件名得到文件URL
	 * @param name
	 * @return
	 */
	private String name2Path(String name) {
		// 得到class文件的URL
		return url + "/"  + name.replace('.', '/') + ".class"; // 注意网络路径表示和File路径表示还是有区别的
	}
}
