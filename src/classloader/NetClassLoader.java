package classloader;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

public class NetClassLoader extends ClassLoader {
	private String url; // class�ļ����ڵ�URL
	public NetClassLoader(String url) {
		this.url = url;
	}
	/**
	 * ��д�����findClass����
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
	 * �������ȡclass�ļ�������������
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
			// ���ܻ��漰���ܣ��ڴ�ʡ�ԣ������Ƿǳ���Ҫ��һ������
			return baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * �����ļ����õ��ļ�URL
	 * @param name
	 * @return
	 */
	private String name2Path(String name) {
		// �õ�class�ļ���URL
		return url + "/"  + name.replace('.', '/') + ".class"; // ע������·����ʾ��File·����ʾ�����������
	}
}
