package M3;

public class Test {

	public static void main(String[] args) {
		Container j1234 = new Container("j1234");
		ContainerStatus test = new ContainerStatus(j1234);
		test.statusUpdate(26.1, 56.5, 200.0);
		test.increment();
		test.statusUpdate(26.3, 56.6, 199.9);
		test.increment();
		test.displayStatus();
	}
}
