package Module3;

public class ThreadState {

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(
                ()-> {
                    Thread currThread = Thread.currentThread();
                    System.out.println("[1] State: "+currThread.getState());
                }
        );
        //We debug a thread using a thread dump which is a snapshot of all the threads in the application with state--
        //-- and also a stack trace of each thread to see where each thread is in that point of execution

        System.out.println("[2] State: "+thread1.getState());
        thread1.start();
        System.out.println("[3] State: "+thread1.getState());
        thread1.join();
        System.out.println("[4] State: "+thread1.getState());

    }
}

/*
Output:

[2] State: NEW
[3] State: RUNNABLE
[1] State: RUNNABLE
[4] State: TERMINATED

 */
