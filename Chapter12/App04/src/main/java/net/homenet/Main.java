package net.homenet;

import redis.clients.jedis.Jedis;

public class Main {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");

        //# 1.
        //jedis.set("message", "Hello, from Jedis!");
        //System.out.println(jedis.get("message"));

        //# 2.
        //jedis.rpush("authors", "Marten Deinum", "Josh Long", "Daniel Rubio", "Gark Mak");
        //System.out.println("Authors: " + jedis.lrange("authors", 0, -1));

        //# 3.
        jedis.hset("sr_3", "authors", "Gark Mak, Danial Rubio, Josh Long, Maten Deinum");
        jedis.hset("sr_3", "published", "2014");
        jedis.hset("sr_4", "authors", "Josh Long, Marten Deinum");
        jedis.hset("sr_4", "published", "2017");

        System.out.println("Spring Recipes 3rd: " + jedis.hgetAll("sr_3"));
        System.out.println("Spring Recipes 4th: " + jedis.hgetAll("sr_4"));
    }
}
