package examples.gc;
/**
 * 此代码帮助深刻理解两点：
 * 1. 对象可以在被GC时被自我拯救：譬如把自己(this关键字)赋值给某个类变量或者对象成员变量
 * 2. 这种自救只有一次机会，因为一个对象的finalize()方法最多只会被调用一次！后来才理解为啥？（面临下次回收不会再走finalize()）
 * 特别说明：
 * 1. 忘掉这个方法
 * 2. 记住它的高消耗性和不确定性
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
//		super.finalize(); // jdk 1.9之后被移除了
		System.out.println("finalize method executed");
		FinalizeEscapeGC.SAVE_HOOK = this;
	}
	public static void main(String[] args) throws InterruptedException {
		SAVE_HOOK = new FinalizeEscapeGC();
		// 对象第一次拯救自己
		SAVE_HOOK = null;
		System.gc();
		// 因为gc()方法优先级很低，所以暂停0.5s等待它
		Thread.sleep(500);
		if (SAVE_HOOK != null) {
			SAVE_HOOK.isAlive();
		} else {
			System.out.println("no, I am dead:(");
		}
		
		// 下面这段代码跟上面完全一样，但是这次却自救失败！！
		SAVE_HOOK = null;
		System.gc();
		// 因为gc()方法优先级很低，所以暂停0.5s等待它
		Thread.sleep(500);
		if (SAVE_HOOK != null) {
			SAVE_HOOK.isAlive();
		} else {
			System.out.println("no, I am dead:(");
		}
	}
}
