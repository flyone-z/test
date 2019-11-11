package redis;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

public class Test {
	
	@org.junit.Test
	public void testName() throws Exception {
		Jedis jedis = new Jedis("192.168.106.33");
		System.out.println(jedis);
		jedis.auth("123456");
		String ping = jedis.ping();
		System.out.println(ping);
		
		jedis.flushAll();
		
		jedis.set("age", "16");
		String string = jedis.get("name");
		System.out.println(string);
		
		Set<String> keys = jedis.keys("*");
		for (String key : keys) {
			System.out.println(key);
		}
		
		jedis.lpush("num", "1");
		jedis.lpush("num", "2");
		jedis.lpush("num", "3");
		List<String> num = jedis.lrange("num", 0, -1);
		for (String n : num) {
			System.out.println(n);
		}
		
		jedis.sadd("girls", "一");
		jedis.sadd("girls", "二");
		jedis.sadd("girls", "三");
		jedis.sadd("girls", "二");
		
		Set<String> girls = jedis.sunion("girls");
		for (String girl : girls) {
			System.out.println(girl);
		}
		
		jedis.hset("user", "name", "one");
		jedis.hset("user", "age", "18");
		jedis.hset("user", "password", "123456");
		jedis.hset("user", "like", "lonely");
		List<String> hmget = jedis.hmget("user","name","age");
		for (String string2 : hmget) {
			System.out.println(string2);
		}
		
	}
	
	@org.junit.Test
	public void testcluster() throws Exception {
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.106.33", 7001));
		nodes.add(new HostAndPort("192.168.106.33", 7002));
		nodes.add(new HostAndPort("192.168.106.33", 7003));
		nodes.add(new HostAndPort("192.168.106.33", 7004));
		//nodes.add(new HostAndPort("192.168.106.33", 7005));
		nodes.add(new HostAndPort("192.168.106.33", 7006));
		nodes.add(new HostAndPort("192.168.106.33", 7007));
		
		JedisCluster jedisCluster = new JedisCluster(nodes);
		
		
		jedisCluster.hsetnx("user", "name", "fly");
		
		String hget = jedisCluster.hget("user", "name");
		System.out.println(hget);
		
		System.out.println("第一个测试项目");
	}
	
}
