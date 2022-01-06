package study.forkjoin;

import java.util.concurrent.RecursiveTask;

public class GetSum extends RecursiveTask<Long> {

    long from = 0;
    long to = 0;

    public GetSum(long from, long to) {
        this.from = from;
        this.to = to;
    }

    @Override
    protected Long compute() {
        long gap = to - from; // 작업의 단위가 충분히 작은지 확인하기 위해

        if (gap < 3) {
            long tempSum = 0;
            for (long loop = from; loop <= to; loop++) {
                tempSum += loop;
            }
            return tempSum;
        }

        long middle= (from+to)/2; // 두 부분으로 작업을 나누어 수행하기 위해 중간값을 찾는다.
        GetSum sumPre = new GetSum(from,middle); //작업 하나 생성
        sumPre.fork(); // 작업(compute)을 ForkJoinPool로 전달한다,
        GetSum sumPost = new GetSum(middle+1,to); //또다른 작업 생성
        return sumPost.compute()+sumPre.join(); // join() poll로 전달한 작업의 완료를 기다린다.
    }

    public long getFrom() {
        return from;
    }

    public long getTo() {
        return to;
    }
}
