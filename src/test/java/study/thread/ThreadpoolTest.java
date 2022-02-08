package study.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ThreadpoolTest {

	private ExecutorService executorService;

	@BeforeEach
	void setUp() {
		executorService = Executors.newFixedThreadPool(3);
	}

	@Test
	void 스레드_작업_종료_대기_awaitTermination() throws InterruptedException {
		Runnable task = () -> System.out.println("작업 스레드 이름: " + Thread.currentThread().getName());
		for (int i = 0; i < 100; i++) {
			executorService.submit(task);
		}
		executorService.shutdown();
		executorService.awaitTermination(10, TimeUnit.SECONDS);
	}

	/** execute() submit() 차이
	 *  execute : 작업 처리 결과를 반환하지 않는다. 처리 도중 예외가 발생하면 스레드 풀에서 제거된다.
	 *  submit : 작업 처리 결과를 반환한다. 처리 도중 예외가 발생해도 스레드를 종료하지 않고 다음 작업에 재사용한다.
	 */

	@Test
	void submit은_오류_나도_종료되지않고_스레드_재사용() throws InterruptedException {
		Runnable task = () -> {
			System.out.println("작업 스레드 이름: " + Thread.currentThread().getName());
			Integer.parseInt("100원");
			System.out.println("오류 이후"+ " : 작업 스레드 이름: " + Thread.currentThread().getName()); // 실행안됨
		};
		for (int i = 0; i < 100; i++) {
			executorService.submit(task);
		}
		executorService.shutdown();
		executorService.awaitTermination(10, TimeUnit.SECONDS);
	}

	@Test
	void execute는_오류_나면_종료하고_새_스레드생성() throws InterruptedException {
		Runnable task = () -> {
			System.out.println("작업 스레드 이름: " + Thread.currentThread().getName());
			Integer.parseInt("100원");
			System.out.println("오류 이후"+ " : 작업 스레드 이름: " + Thread.currentThread().getName()); // 실행안됨
		};
		for (int i = 0; i < 100; i++) {
			executorService.execute(task);
		}
		executorService.shutdown();
		executorService.awaitTermination(10, TimeUnit.SECONDS);
	}
}
