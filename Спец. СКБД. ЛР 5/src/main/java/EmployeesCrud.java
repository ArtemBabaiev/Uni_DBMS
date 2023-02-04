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
 * @class EmployeesCrud
 */
public class EmployeesCrud implements IHash {
    private Jedis jedis;

    public EmployeesCrud(Jedis jedis) {
        this.jedis = jedis;
    }

    public void Create(String id, String fullName, String workPlace) {
        Map<String, String> data = new HashMap<String, String>();
        data.put("fullName", fullName);
        data.put("workPlace", workPlace);
        jedis.hmset("employees:" + id, data);
    }


    @Override
    public void UpdateField(String id, HashMap<String, String> newData) {
        jedis.hmset("employees:" + id, newData);
    }

    @Override
    public void DeleteField(String id, String field) {
        jedis.hdel("employees:" + id, field);
    }

    @Override
    public void Delete(String id) {
        jedis.del("employees:" + id);
    }

    @Override
    public Map<String, String> Get(String id) {
        return jedis.hgetAll("employees:" + id);
    }

    @Override
    public String GetField(String id, String field) {
        return jedis.hget("employees:" + id, field);
    }

    @Override
    public Set<String> GetAllKeys(String id) {
        return jedis.hkeys("employees:" + id);
    }

    @Override
    public List<String> GetAllValues(String id) {
        return jedis.hvals("employees:" + id);
    }

    @Override
    public long GetLength(String id) {
        return jedis.hlen("employees:" + id);
    }

    @Override
    public void DeleteAll() {
        Set<String> keys = jedis.keys("employees:*");
        for (String key : keys) {
            jedis.del(key);
        }
    }
}
