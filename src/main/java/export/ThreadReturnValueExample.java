package export;


class MyThread1 extends Thread {
    private int result;

    public int getResult() {
        return result;
    }

    public void run() {
        // 执行耗时操作，计算结果
        result = 42;
    }
}

public class ThreadReturnValueExample {
    public static void main(String[] args) throws InterruptedException {
        MyThread1 thread = new MyThread1();
        thread.start();

        thread.join(); // 等待线程执行完毕

        int result = thread.getResult();
        System.out.println("Thread result: " + result);
        System.out.println("Thread result: " + Thread.currentThread().getName() + result);
    }
}
