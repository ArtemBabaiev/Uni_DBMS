import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author artem
 * @version 1.0
 * @project Спец. СКБД. ЛР 5
 * @date 04.05.2022 15:47
 * @class LibrariesCrud
 */
public class LibrariesCrud implements IHash{
    private Jedis jedis;

    public LibrariesCrud(Jedis jedis)  {
        this.jedis = jedis;
    }

    public void Create(String number, String location, String roomsQuantity){
        Map<String, String> data = new HashMap<String, String>();
        data.put("location", location);
        data.put("roomsQuantity", roomsQuantity);
        jedis.hmset("libraries:" + number, data);
    }


    @Override
    public void UpdateField(String number, HashMap<String, String> newData) {
        jedis.hmset("libraries:" + number, newData);
    }

    @Override
    public void DeleteField(String number, String field) {
        jedis.hdel("libraries:" + number, field);
    }

    @Override
    public void Delete(String number) {
        jedis.del("libraries:" + number);
    }

    @Override
    public Map<String, String> Get(String number) {
        return jedis.hgetAll("libraries:" + number);
    }

    @Override
    public String GetField(String number, String field) {
        return jedis.hget("libraries:" + number, field);
    }

    @Override
    public Set<String> GetAllKeys(String number) {
        return jedis.hkeys("libraries:" + number);
    }

    @Override
    public List<String> GetAllValues(String number) {
        return jedis.hvals("libraries:" + number);
    }

    @Override
    public long GetLength(String number) {
        return jedis.hlen("libraries:" + number);
    }

    @Override
    public void DeleteAll() {
        Set<String> keys = jedis.keys("libraries:*");
        for (String key : keys) {
            jedis.del(key);
        }
    }
}
