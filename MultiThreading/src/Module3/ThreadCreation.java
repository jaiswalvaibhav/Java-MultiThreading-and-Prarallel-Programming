package Module3;

public class ThreadCreation {
//Thread object using Thread class and basic methods
/*
    public static void main(String[] args) throws InterruptedException {
        Thread thread  = Thread.currentThread();
        System.out.println("Current thread: " + thread.getName());

        Thread.sleep(3000);

        System.out.println("Current thread: " + thread.getName());
    }
 */

//Ways to create Threads:
//1. Extend the Thread class

/*    public static void main(String[] args) {
        MyThread myThread = new MyThread();
//        myThread.run(); // this is not correct coz this will execute run method from main thread and we'll get output as: Current thread: main
        myThread.start();
    }

    static  class MyThread extends Thread{
        public void run() {
            setName("MyThread-0");// If this is run by main thread, the name of the thread remains as "main"
            System.out.println("Current thread: " + Thread.currentThread().getName());
        }
    }
*/

//2. Use Runnable interface

    public static void main(String[] args) throws  InterruptedException{
        System.out.println("[1] Current Thread: "+ Thread.currentThread().getName());
//        Runnable runnable = ()-> {
//                System.out.println("[2] Current Thread: "+ Thread.currentThread().getName());
//        };
//        Thread thread = new Thread(runnable);
        //OR
        Thread thread = new Thread(()-> {
            System.out.println("[2] Current Thre ad: "+ Thread.currentThread().getName());
        });
        thread.setName("MyThread-0");
        thread.start();
        //When we run this, the order in which the main and child thread executes is not in a fixed order.
        //Output:
        /*
            [1] Current Thread: main
            [3] Current Thread: main
            [2] Current Thread: MyThread-0
         */
        //Thus, we use join that completes the child thread execution at that given step
        thread.join();//this will make the thread execution in order

        System.out.println("[3] Current Thread: "+ Thread.currentThread().getName());

    }
}