package ForkJoin;

import java.util.concurrent.RecursiveTask;
/*
* jdk 1.7 并行流
* */

public class ForkJoinCalculate extends RecursiveTask<Long> {

    private static final long serialversionUID = 1234564156151654L;

    private long start;
    private long end;

    private static final long THRESHOLD = 10000;

    public ForkJoinCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length=end - start;
        if(length<THRESHOLD)
        {
            long sum =0;
            for(long i=start;i<=end;i++)
            {
                sum+=i;
            }
            return sum;
        }else
        {
            long middle = (start+end)/2;
            ForkJoinCalculate left = new ForkJoinCalculate(start,middle);
            //拆分子任务，同时压入线程队列中
            left.fork();

            ForkJoinCalculate right =new ForkJoinCalculate(middle+1,end);
            right.fork();

            return left.join()+right.join();
        }

    }






















}
