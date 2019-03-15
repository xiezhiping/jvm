package oom;

import java.lang.reflect.Field;

public class DirectMemoryOOM {
	private static final int _1MB = 1024*1024;
	public static void main(String[] args) {
		Field unsafeField = sun.misc.Unsafe.class.getDeclaredFields()[0];
		System.out.println(unsafeField.getName());
		unsafeField.setAccessible(true);
		sun.misc.Unsafe unsafe = null;
		try {
			unsafe = (sun.misc.Unsafe)unsafeField.get(null);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(true) {
			unsafe.allocateMemory(_1MB);
//			unsafe.freeMemory(_1MB);
		}
	}
}
