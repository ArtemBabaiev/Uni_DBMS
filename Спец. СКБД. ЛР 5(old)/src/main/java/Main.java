import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.resps.Tuple;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author artem
 * @version 1.0
 * @project Спец. СКБД. ЛР 5
 * @date 27.04.2022 21:32
 * @class Main
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        var jedis = new Jedis("http://localhost:6379");
        System.out.println("\n**************************STRINGS**************************\n");
        StringCRUD(jedis);

        System.out.println("\n**************************HASH**************************\n");
        HashCRUD(jedis);

        System.out.println("\n**************************List**************************\n");
        ListCRUD(jedis);

        System.out.println("\n**************************Set**************************\n");
        SetCRUD(jedis);

        System.out.println("\n**************************Sorted Set**************************\n");
        SSetCRUD(jedis);

        System.out.println("\n**************************Keys**************************\n");
        KeyCRUD(jedis);
    }
    public static void StringCRUD (Jedis jedis){
        String stringKey = "students:babaiev";
        jedis.set(stringKey, "{name: artem, university: CHNU, group: 243B, id: 1}");

        System.out.println("****String get: ****");
        System.out.println(jedis.get(stringKey));

        System.out.println("****String length: ****");
        System.out.println(jedis.strlen(stringKey));

        System.out.println("****String range: ****");
        System.out.println(jedis.getrange(stringKey, 0, 40));

        System.out.println("****String append: ****");
        jedis.append(stringKey, "extra !data!");
        System.out.println(jedis.get(stringKey));

        System.out.println("****Increment and decrement: ****");
        System.out.println(jedis.incr(stringKey + ":about"));
        System.out.println(jedis.incrBy(stringKey + ":about", 5));
        System.out.println(jedis.decr(stringKey + ":about"));
        System.out.println(jedis.decrBy(stringKey + ":about", 2));

        jedis.del(stringKey);
        jedis.del(stringKey + ":about");
    }

    public static void HashCRUD (Jedis jedis){
        String key = "students:hash_babaiev";
        jedis.hset(key, "name", "artem");
        jedis.hsetnx(key, "name", "artem0");
        jedis.hsetnx(key, "university", "CHNU");
        HashMap<String, String> data = new HashMap<>();
        data.put("group", "243B");
        data.put("id", "1");
        data.put("age", "19");
        jedis.hmset(key, data);

        System.out.println("****Hash gets demonstration: ****");
        System.out.println(jedis.hgetAll(key));
        System.out.println(jedis.hget(key, "name"));
        System.out.println(jedis.hmget(key, "name", "age"));
        System.out.println(jedis.hkeys(key));
        System.out.println(jedis.hvals(key));

        System.out.println("****Hash length: ****");
        System.out.println(jedis.hlen(key));

        System.out.println("****Hash delete field: ****");
        jedis.hdel(key, "age", "group");
        System.out.println(jedis.hgetAll(key));

        jedis.del(key);

    }

    public static void ListCRUD (Jedis jedis){
        String key = "list_babaiev";
        jedis.lpush(key,  "put_tea", "put_sugar");
        jedis.rpush(key, "wash_cup");
        jedis.lpushx(key, "pour_water");
        jedis.rpushx(key, "boil.water");
        jedis.rpush(key+"0", "testValue");

        System.out.println("****List gets: ****");
        System.out.println(jedis.lrange(key, 0, 5));
        System.out.println(jedis.lindex(key, 2));

        System.out.println("****List length: ****");
        System.out.println(jedis.llen(key));

        System.out.println("****List pops: ****");
        jedis.lpop(key);
        jedis.rpop(key);
        System.out.println(jedis.lrange(key, 0, 5));

        System.out.println("****List remove: ****");
        jedis.rpush(key, "a");
        jedis.rpush(key, "a");
        jedis.rpush(key, "a");
        jedis.rpush(key, "a");
        System.out.println(jedis.llen(key));
        jedis.lrem(key, 3, "a");
        System.out.println(jedis.llen(key));

        System.out.println("****List update by index: ****");
        jedis.lset(key, 2, "updated value");
        System.out.println(jedis.lrange(key, 0, 10));

        jedis.del(key);
    }

    public static void SetCRUD (Jedis jedis){
        String key1 = "set_1";
        String key2 = "set_2";
        jedis.sadd(key1 , "HMI", "History", "English", "DB", "EMofSE", "Web", "OS");
        jedis.sadd(key2 , "Java", "English", "Networks", "Requirement_analysis", "Spec_DBMS", "Web", "Team_Dev");

        System.out.println("**** Set gets: ****");
        System.out.println(jedis.smembers(key1));
        System.out.println(jedis.smembers(key2));

        System.out.println("**** Set is member: ****");
        System.out.println(jedis.sismember(key1, "HMI"));
        System.out.println(jedis.sismember(key2, "HMI"));

        System.out.println("**** Set length: ****");
        System.out.println(jedis.scard(key1));

        jedis.sunionstore("union", key2, key1);
        jedis.sinterstore("inter", key2, key1);
        jedis.sdiffstore("diff", key2, key1);
        System.out.println("**** Set operation: ****");
        System.out.println("Union: " + jedis.smembers("union"));
        System.out.println("Intersection: " + jedis.smembers("inter"));
        System.out.println("Difference: " + jedis.smembers("diff"));

        System.out.println("**** Set pop and del: ****");
        jedis.srem(key2, "Java", "Spec_DBMS");
        jedis.spop(key1, 3);
        jedis.spop(key2);
        System.out.println(jedis.scard(key1));
        System.out.println(jedis.scard(key2));

        jedis.del(key1, key2, "union", "inter", "diff");
    }

    public static void SSetCRUD (Jedis jedis){
        String key = "sset_babaiev";
        HashMap<String, Double> data = new HashMap<>();
        data.put("English", 99.0);
        data.put("OS", 80.0);
        data.put("Web", 100.0);
        data.put("DB", 100.0);
        data.put("HMI", 97.0);
        data.put("History", 90.0);
        jedis.zadd(key, data);

        System.out.println("****SSet range: ****");
        System.out.println(jedis.zrange(key, 0, -1));
        System.out.println(jedis.zrevrange(key, 0, -1));

        System.out.println("****SSet value and score: ****");
        List<Tuple> tuples = jedis.zrevrangeWithScores(key, 0, -1);
        for(Tuple tuple: tuples){
            System.out.println(tuple.getElement() + "-" + tuple.getScore());
        }

        System.out.println("****SSet length: ****");
        System.out.println(jedis.zcard(key));

        System.out.println("****SSet count: ****");
        System.out.println(jedis.zcount(key, 91, 100));

        System.out.println("****SSet score: ****");
        System.out.println(jedis.zscore(key, "DB"));

        System.out.println("****SSet increment: ****");
        jedis.zincrby(key, 5 , "OS");
        System.out.println(jedis.zscore(key, "OS"));

        System.out.println("****SSet rank: ****");
        System.out.println(jedis.zrevrank(key, "OS"));
        System.out.println(jedis.zrank(key, "OS"));

        jedis.del(key);


    }

    public static void KeyCRUD (Jedis jedis) throws InterruptedException {
        jedis.set("key1", "1");
        jedis.set("key2", "1");
        jedis.set("key3", "1");
        jedis.set("key4", "1");
        jedis.set("key51", "1");

        jedis.expire("key1", 300);
        jedis.expireAt("key2",1689627600);

        TimeUnit.SECONDS.sleep(5);

        System.out.println("**** Time to live ****");
        System.out.println(jedis.ttl("key1"));

        jedis.persist("key1");

        System.out.println("All keys: " + jedis.keys("*"));
        System.out.println("Keys starting with k ending with 1: " + jedis.keys("k*1"));

        jedis.del("key3");

        System.out.println("if exist: " + jedis.exists("key3"));

        System.out.println("Type: " + jedis.type("key2"));

        System.out.println("Serialized: "  + jedis.dump("key2"));

        jedis.del("key1", "key2","key3","key4","key51");
    }
}
