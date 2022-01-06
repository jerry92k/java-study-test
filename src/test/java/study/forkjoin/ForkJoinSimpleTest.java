package study.forkjoin;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ForkJoinSimpleTest {

    @Test
    void fork_join_이용한_연산(){
        long from = 0;
        long to = 10;

        GetSum sum=new GetSum(from,to);
        ForkJoinSimple forkJoinSimple = new ForkJoinSimple();
        forkJoinSimple.calculate(sum);
    }
}
