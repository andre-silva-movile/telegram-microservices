package message.core.redis;

import java.io.Serializable;

public abstract class RedisQueueConsumer<T extends Serializable> implements Runnable {

    private final RedisQueue<T> queue;
    private volatile boolean running;

    public RedisQueueConsumer(RedisQueue<T> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        running = true;
        while (running && !Thread.currentThread().isInterrupted()) {
            T update = queue.pop();
            if (update != null) {
                this.process(update);
            }
        }
    }

    abstract public void process(T messageWrapper);

    public void stop() {
        this.running = false;
    }
}
