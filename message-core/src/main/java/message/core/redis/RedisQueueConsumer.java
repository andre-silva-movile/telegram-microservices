package message.core.redis;

import message.core.log.Log;

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
            try{
                T update = queue.pop();
                if (update != null) {
                    this.process(update);
                }
            } catch (RuntimeException e){
                Log.core.error("Unable to process pooling message", e.toString());
            }
        }
    }

    abstract public void process(T messageWrapper);

    public void stop() {
        this.running = false;
    }
}
