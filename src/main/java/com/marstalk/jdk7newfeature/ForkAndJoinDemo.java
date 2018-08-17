package com.marstalk.jdk7newfeature;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkAndJoinDemo {
    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        forkJoinPool.invoke(new MyRecursiveAction(51));

        Thread.sleep(20 * 000);
    }
}


class MyRecursiveAction extends RecursiveAction {
    private int workload;

    public MyRecursiveAction(int workload) {
        System.out.println("new action with workload:" + workload);
        this.workload = workload;
    }

    @Override
    protected void compute() {
        if (workload > 10) {
            List<MyRecursiveAction> myRecursiveActions = splitAction();
            for (MyRecursiveAction m : myRecursiveActions) {
                m.fork();
            }
        } else {
            System.out.println("do job myself:" + this.workload);
        }
    }

    private List<MyRecursiveAction> splitAction() {
        System.out.println("fork workload:" + this.workload);
        MyRecursiveAction myRecursiveAction = new MyRecursiveAction(workload / 2);
        MyRecursiveAction myRecursiveAction1 = new MyRecursiveAction(workload / 2);
        List<MyRecursiveAction> myRecursiveActions = new ArrayList<>(2);
        myRecursiveActions.add(myRecursiveAction);
        myRecursiveActions.add(myRecursiveAction1);
        return myRecursiveActions;
    }
}
