package study.forkjoin;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinSimple {

    static final ForkJoinPool mainPool = new ForkJoinPool();

    public void calculate(GetSum sum) {
        Long result = mainPool.invoke(sum);
        System.out.println("Fork join : total sum of "+sum.getFrom()+" ~ "+sum.to+" = "+result);
    }
}
