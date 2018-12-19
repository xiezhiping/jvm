package examples.gc;
/**
 * �˴���������������㣺
 * 1. ��������ڱ�GCʱ���������ȣ�Ʃ����Լ�(this�ؼ���)��ֵ��ĳ����������߶����Ա����
 * 2. �����Ծ�ֻ��һ�λ��ᣬ��Ϊһ�������finalize()�������ֻ�ᱻ����һ�Σ����������Ϊɶ���������´λ��ղ�������finalize()��
 * �ر�˵����
 * 1. �����������
 * 2. ��ס���ĸ������ԺͲ�ȷ����
 * @author xzp
 *
 */
public class FinalizeEscapeGC {
	public static FinalizeEscapeGC SAVE_HOOK = null;
	public void isAlive() {
		System.out.println("yes, I am still alive:)");
	}
//	@SuppressWarnings("deprecation")
	@Override
	protected void finalize() throws Throwable {
//		super.finalize(); // jdk 1.9֮���Ƴ���
		System.out.println("finalize method executed");
		FinalizeEscapeGC.SAVE_HOOK = this;
	}
	public static void main(String[] args) throws InterruptedException {
		SAVE_HOOK = new FinalizeEscapeGC();
		// �����һ�������Լ�
		SAVE_HOOK = null;
		System.gc();
		// ��Ϊgc()�������ȼ��ܵͣ�������ͣ0.5s�ȴ���
		Thread.sleep(500);
		if (SAVE_HOOK != null) {
			SAVE_HOOK.isAlive();
		} else {
			System.out.println("no, I am dead:(");
		}
		
		// ������δ����������ȫһ�����������ȴ�Ծ�ʧ�ܣ���
		SAVE_HOOK = null;
		System.gc();
		// ��Ϊgc()�������ȼ��ܵͣ�������ͣ0.5s�ȴ���
		Thread.sleep(500);
		if (SAVE_HOOK != null) {
			SAVE_HOOK.isAlive();
		} else {
			System.out.println("no, I am dead:(");
		}
	}
}
