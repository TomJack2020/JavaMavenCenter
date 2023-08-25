package export;


import java.util.ArrayList;

class RunFunc {

    public static int GetSum(int num){
        int sum = 0;
        for(int i = 0; i <= num; i++){
            sum += i;
        }
        return sum;
    }

}


class MyThread extends Thread {

    private int result;
    private int age;
    private String name;

    public void setParameter(int age) {
        this.age = age;
    }

    public int getResult(){
        return result;
    }

    public void run() {

        // 执行耗时操作，计算结果
        result = RunFunc.GetSum(age);
        System.out.println(Thread.currentThread().getName() + " - Count: " + age);

//        System.out.println(result);

//        for (int i = 1; i <= 5; i++) {
//            System.out.println("do some " + age);
//            System.out.println(Thread.currentThread().getName() + " - Count: " + i);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }
}

public class MultiThreadExample {
    public static void main(String[] args) throws InterruptedException {


        ArrayList<MyThread> threadList = new ArrayList<>();
        for(int i=0; i< 100; i++){
            MyThread thread =new MyThread();
            thread.setParameter(1000 + i * 5);
            threadList.add(thread);
        }
        for(MyThread thread : threadList){
            thread.start();
            thread.join();
            int result = thread.getResult();
            System.out.println("Thread result: " + Thread.currentThread().getName() + "计算结果:" +  result);
        }

//        MyThread thread1 = new MyThread();
//        MyThread thread2 = new MyThread();
//        thread1.setParameter(1000);
//        thread1.start(); // 启动线程1
//        thread2.start(); // 启动线程2
//
//        thread1.join(); // 等待线程1完毕
//        thread2.join(); // 等待线程2完毕
//
//
//        int result = thread1.getResult();
//        System.out.println("Thread result: " + Thread.currentThread().getName() + "计算结果" +  result);


//        // 主线程继续执行其他操作
//        for (int i = 1; i <= 5; i++) {
//            System.out.println(Thread.currentThread().getName() + " - Main Count: " + i);
//            try {
//                Thread.sleep(1500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
