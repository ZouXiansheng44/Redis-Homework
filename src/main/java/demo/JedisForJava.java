package demo;
import redis.clients.jedis.Jedis;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

public class JedisForJava {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost",6379);
        System.out.println("连接成功");
        Blog blog =new Blog();
        blog.setTitle("fastjson");
        blog.setAuthor("author");
        blog.setContent("zps");
        blog.setTime("20191029");
        Long id=save(blog,jedis);
        System.out.println(id);
        System.out.println("保存成功");
        Blog blog1=get(jedis,id);
        System.out.println(blog1);
        System.out.println("查找成功");
        blog.setTime("20191029");
        Long pid=update(blog,jedis,id);
        Blog blog2=get(jedis,pid);
        System.out.println(blog2);
        Long a=delete(jedis,id);
        System.out.println(a);
    }
    public static Long save(Blog blog,Jedis jedis){
        Long id=jedis.incr("posts");
        Map<String,String> map=new HashMap<String, String>();
        map.put("title",blog.getTitle());
        map.put("author",blog.getAuthor());
        map.put("content",blog.getContent());
        map.put("time",blog.getTime());
        jedis.hmset("post:"+id+":data",map);
        return id;
    }
    public static Blog get(Jedis jedis,Long id){
        Map<String,String> map = jedis.hgetAll("post:"+id+":data");
        Blog blog=new Blog();
        blog.setTitle(map.get("title"));
        blog.setAuthor(map.get("author"));
        blog.setContent(map.get("content"));
        blog.setTime(map.get("time"));
        return blog;
    }
    public static Long delete(Jedis jedis,Long id){
        long a=jedis.hdel("post:"+id+":data");
        return a;
    }
    public static Long update(Blog blog,Jedis jedis,Long id){
        Long pid=id;
        Map<String,String> map=new HashMap<String, String>();
        map.put("title",blog.getTitle());
        map.put("author",blog.getAuthor());
        map.put("content",blog.getContent());
        map.put("time",blog.getTime());
        jedis.hmset("post:"+id+":data",map);
        return pid;
    }

}
