public class ThreadDemo {
    public static void main(String[] args) {
        // 传统方式创建
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("传统方式创建！");
        
            }
        });
        // Lambda表达式创建
        Thread t2 = new Thread(() -> {
            System.out.println("Lambda表达式创建！");
        });
        // 更简洁
        Thread t3 = new Thread(() -> System.out.println("更简洁！"));
        // 启动线程
        t1.start();
        t2.start();
        t3.start();
    
        // 创建多个线程执行不同任务
        for (int i = 0; i < 5; i++) {
            final int threadNumber = i;
            new Thread(() -> {
                System.out.println("线程" + threadNumber + "开始执行！");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("线程" + threadNumber + "执行完毕！");
            }).start();
        }
    
    }
}
