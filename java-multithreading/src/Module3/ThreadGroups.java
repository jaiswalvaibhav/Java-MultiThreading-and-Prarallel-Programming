package Module3;

public class ThreadGroups {

    /*
    Summary of below code when executed:
    1) The main thread creates a ThreadGroup with max priority 7, adds three threads to it (one with priority set to 10 but capped at 7), starts them, and then sleeps for 4 seconds.
    2) All three threads begin concurrently, each entering an infinite loop where they sleep for 4 seconds using Thread.sleep(4000).
    3) After the main thread wakes, it interrupts the group in 3 secs; threads catch the InterruptedException, print their name and priority, and continue the loop to sleep again.
     */


    public static void main(String[] args) throws InterruptedException {
//        Thread thread1 = new Thread(()-> System.out.println("Thread1"));
//        Thread thread2 = new Thread(()-> System.out.println("Thread2"));
//        Thread thread3 = new Thread(()-> System.out.println("Thread3"));
//        Thread thread4 = new Thread(()-> System.out.println("Thread4"));
//
//        thread1.start();
//        thread1.join();

//        We need to manage the start and join of each thread
//        Rather we can use ThreadGroup which is a collection of threads

//    Parent and Child thread group
/*
        ThreadGroup subGroup = new ThreadGroup("subGroup");
        ThreadGroup group = new ThreadGroup(subGroup, "Group1");//Parent child reln

        //group.getParent();
        //group.parentOf(subGroup);
*/

        //Adding threads to thread group
        ThreadGroup group = new ThreadGroup("group");
        group.setMaxPriority(7);
        //Now group thread of method has max priority 7
        //If it was: group.setMaxPriority(Thread.NORM_PRIORITY);//Then Max priority of any thread in this group will be NORM_PRIORITY
        //We won't be able to add any thread with priority more than that of min(NORM_PRIORITY, Parent Thread Priority)

        ThreadGroup parent = group.getParent();
        System.out.println("Parent Thread Name: "+parent.getName() + " priority "+ parent.getMaxPriority());

//        Thread thread1 = new Thread(group, () -> System.out.println("Thread1"));
        Thread thread1 = new Thread(group, new MyThread(), "Thread1");//This implementation uses below created MyThread class' run method rather than the lambda function used in above line
        Thread thread2 = new Thread(group, new MyThread(), "Thread2");
        Thread thread3 = new Thread(group, new MyThread(), "Thread3");

        thread1.setPriority(Thread.MAX_PRIORITY);

        thread1.start(); // starts and runs MyThread.run() in thread1 and goes to sleep for 3 secs
        thread2.start(); // starts and runs MyThread.run() in thread2 and goes to sleep for 3 secs
        thread3.start(); // starts and runs MyThread.run() in thread3 and goes to sleep for 3 secs
        //All 3 threads (thread1, thread2, thread3) begin executing around the same time concurrently, with each in its own separate thread of execution.
        //each thread will complete the sleep around same time
        //This makes the thread go from RUNNABLE state to TIMED_WAITING state

        System.out.println("Sleeping for 3 seconds...");
        Thread.sleep(3000);//The main thread concurrently sleeps for 3 secs

        group.interrupt();//It calls recursively interrupt on all threads just like thread1.interrupt();
        //Gives InterruptedException when interrupt() is used
        //This is interrupt all 3 thread in 3 secs and close then makes the threads to RUNNABLE state


        //Daemon thread is not required for JVM to close
        /*
         group.setDaemon(true);
//       By default the JVM stops when all the threads stop
//       But if we make a thread as daemon, then it doesn't wait for that thread to end. JVM closes directly.
         */
    }

    static class MyThread implements Runnable{

        @Override
        public void run(){
            while (true){
                try {
                    Thread.sleep(4000);
                }
                catch (InterruptedException e){
                    Thread currThread = Thread.currentThread();
                    System.out.println("Interrupt "+ currThread.getName()+ " with priority: "+currThread.getPriority());
//                    e.printStackTrace();
                }
            }
        }
    }
}
