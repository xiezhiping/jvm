package examples.memory;

import java.util.ArrayList;
import java.util.List;

public class MonitoringTest {
	public static void fillHeap(int num) throws InterruptedException {
		List<OOMObject> list = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			// ��΢��ʱ����������ı仯��������
			Thread.sleep(50);
			list.add(new OOMObject());
			System.out.println("i:" + i);
		}
		System.gc();
	}
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		fillHeap(1000);
	}

}
class OOMObject {
	public byte[] placeholder = new byte[64*1024];
}