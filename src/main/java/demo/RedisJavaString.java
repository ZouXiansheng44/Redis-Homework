package demo;

import redis.clients.jedis.Jedis;

public class RedisJavaString {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");
        jedis.set("successs","www.zime.zj.cn");
        System.out.println("redis存储字符串为："+jedis.get("successs"));
    }
}
