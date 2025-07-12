package Module3;

public class ThreadPriorities {

    //Thread Priorities
    public static void main(String[] args) throws InterruptedException {
        //When we create a new thread, its default priority is same as that of main/parent thread which is 5 (by default)
        //Max Priority is 10 and Min priority is 1
        //Setting priority of main thread
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);

        Thread thread1 = new Thread(
                ()-> {
                    Thread currThread = Thread.currentThread();
                    System.out.println(currThread.getName());
                }
        );
        thread1.setName("Thread_1");
        thread1.setPriority(Thread.MAX_PRIORITY);

        Thread thread2 = new Thread(
                ()-> {
                    Thread currThread = Thread.currentThread();
                    System.out.println(currThread.getName());
                }
        );
        thread2.setName("Thread_2");
        thread2.setPriority(Thread.MIN_PRIORITY);


        //Thread scheduler of java runtime takes the threads with start method and places then on CPU
        //Thread priority order doesn't mean that the thread 1 will be scheduled over thread 2 all the time
        //We shouldn't base our program correctness based on the thread priority
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}