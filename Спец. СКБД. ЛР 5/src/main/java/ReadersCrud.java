import redis.clients.jedis.Jedis;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author artem
 * @version 1.0
 * @project Спец. СКБД. ЛР 5
 * @date 04.05.2022 15:47
 * @class ReadersCrud
 */
public class ReadersCrud implements IHash{
    private Jedis jedis;

    public ReadersCrud(Jedis jedis) {
        this.jedis = jedis;
    }

    public void Create(String id, String fullName, String phoneNumber,
                       String residenceAddress, LocalDate birthDate) {
        Map<String, String> data = new HashMap<String, String>();
        data.put("fullName", fullName);
        data.put("phoneNumber", phoneNumber);
        data.put("residenceAddress", residenceAddress);
        data.put("birthDate", birthDate.toString());
        jedis.hmset("readers:" + id, data);
    }
    @Override
    public void UpdateField(String id, HashMap<String, String> newData) {
        jedis.hmset("readers:" + id, newData);
    }

    @Override
    public void DeleteField(String id, String field) {
        jedis.hdel("readers:" + id, field);
    }

    @Override
    public void Delete(String id) {
        jedis.del("readers:" + id);
    }

    @Override
    public Map<String, String> Get(String id) {
        return jedis.hgetAll("readers:" + id);
    }

    @Override
    public String GetField(String id, String field) {
        return jedis.hget("readers:" + id, field);
    }

    @Override
    public Set<String> GetAllKeys(String id) {
        return jedis.hkeys("readers:" + id);
    }

    @Override
    public List<String> GetAllValues(String id) {
        return jedis.hvals("readers:" + id);
    }

    @Override
    public long GetLength(String id) {
        return jedis.hlen("readers:" + id);
    }

    @Override
    public void DeleteAll() {
        Set<String> keys = jedis.keys("readers:*");
        for (String key : keys) {
            jedis.del(key);
        }
    }
}
