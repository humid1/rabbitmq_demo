package com.humid.webflux.reactor;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

/**
 * @author qiujianping
 * @date Created in 2021/8/13 14:02
 */
public class ReactorDemo {
    public static void main(String[] args) {
        // reactor => jdk8 stream + jdk9 reactive stream
        // Mono 0-1个元素
        // Flux 0-N个元素
        String[] strs = {"1", "2", "3"};
        // 定义订阅者
        Subscriber<Integer> subscriber = new Subscriber<Integer>() {

            private Subscription subscription;

            @Override
            public void onSubscribe(Subscription s) {
                this.subscription = s;
                // 请求一个数据
                subscription.request(1);
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("接收到的数据：" + integer);
                // 处理完调用request在请求一个数据
                this.subscription.request(1);
                // 或者已经达到目标，调用cacel告诉发布者不在接收数据
                // this.subscription.cancel();
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("消费发生异常：" + t.getMessage());
                this.subscription.cancel();
            }

            @Override
            public void onComplete() {
                System.out.println("全部消费完成后执行的方法：onComplete");
            }
        };

        Flux.fromArray(strs).map(Integer::parseInt).subscribe(subscriber);

    }
}
