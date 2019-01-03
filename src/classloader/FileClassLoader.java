package classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * ���ض�·�����ļ���ȡ���File�������
 * @author XZP
 *
 */
public class FileClassLoader extends ClassLoader {
	private String rootDir;
	public FileClassLoader(String rootDir) {
		this.rootDir = rootDir;
	}
	/**
	 * ��д�����findClass����
	 * @param name �ļ���
	 * @return �ҵ�����ֽ������defineClass���ض�Ӧ�Ķ���
	 * @throws ClassNotFoundException
	 */
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		// ��ȡ���class�ļ��ֽ�����
		byte[] classData = getClassDataFromFile(name);
		if (classData == null) {
			throw new ClassNotFoundException();
		} else {
			// ֱ������class����
			return defineClass(name, classData, 0, classData.length);
		}
	}
	/**
	 * ��ȡclass�ļ���ת��Ϊ�ֽ��������߼�
	 * @param name �ļ���
	 * @return
	 */
	private byte[] getClassDataFromFile(String name) {
		// ��ȡ���ļ����ֽ�
		String path = name2Path(name);
		System.out.println("path:" + path);
		try {
			InputStream ins = new FileInputStream(path);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int bufferSize = 4096;
			byte[] buffer = new byte[bufferSize];
			int bytesNumRead = 0;
			// ��ȡ�ļ��ֽ���
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
	 * ͨ���ļ�����ȡclass�ļ�����ȫ·��
	 * @param className �ļ���
	 * @return
	 */
	private String name2Path(String className) {
		return rootDir +  File.separatorChar + className.replace('.', File.separatorChar) + ".class";
	}
}
