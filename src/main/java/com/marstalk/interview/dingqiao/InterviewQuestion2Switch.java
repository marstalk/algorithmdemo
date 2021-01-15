package com.marstalk.interview.dingqiao;

/**
 * 这道题靠擦的是switc case的工作机制。<br/>
 * 我理解错了。<br/>
 * 应该是switch只会case一次。找到分支之后，代码就会接着往下执行，包括这个case后面的其他代码（case和default)，知道遇到break。<br/>
 * 而这个break往往就是容易忽略从而产生bug的地方。<br/>
 * default分支是当switch的时候没有匹配到，才会调用。<br/>
 * 下面例子的执行过程是这样的：<br/>
 * switch type=2,则会跳到case 2 这里。因为没有break，所以会继续执行后面的代码，包括case 3 和default。所以最后结果输出2。<br/>
 */
public class InterviewQuestion2Switch {
    public static void main(String[] args) {
        chooseBest(2);
    }

    private static void chooseBest(int type) {
        int typeCounts = 0;
        switch (type) {
            case 1:
                typeCounts += 1;
                System.out.println("A");
            case 2:
                System.out.println("B");
            case 3:
                typeCounts += 3;
                System.out.println("C");
            default:
                typeCounts += -1;
                System.out.println("D");
        }
        System.out.println(typeCounts);
    }
}
