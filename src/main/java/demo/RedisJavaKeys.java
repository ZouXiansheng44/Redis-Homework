package demo;

import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.Set;

public class RedisJavaKeys {
    public static void main(String[] args) {
        //实例化一个Jedis对象
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");

        // 获取数据并输出
        Set<String> keys = jedis.keys("*");
        Iterator<String> it=keys.iterator() ;
        while(it.hasNext()){
            String key = it.next();
            System.out.println(key);
        }
    }
}
