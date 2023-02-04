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
 * @class LiteratureCrud
 */
public class LiteratureCrud implements IHash {
    private Jedis jedis;

    public LiteratureCrud(Jedis jedis) {
        this.jedis = jedis;
    }

    public void Create(String nomenclatureNumber, String type, String readingPlace, String deadlineForLending,
                       String name, String author, String publisher, String yearOfPublishing) {

        Map<String, String> data = new HashMap<String, String>();
        data.put("type", type);
        data.put("readingPlace", readingPlace);
        data.put("deadlineForLending", deadlineForLending);
        data.put("name", name);
        data.put("author", author);
        data.put("publisher", publisher);
        data.put("yearOfPublishing", yearOfPublishing);
        jedis.hmset("literature:" + nomenclatureNumber, data);
    }

    @Override
    public void UpdateField(String nomenclatureNumber, HashMap<String, String> newData) {
        jedis.hmset("literature:" + nomenclatureNumber, newData);
    }

    @Override
    public void DeleteField(String nomenclatureNumber, String field) {
        jedis.hdel("literature:" + nomenclatureNumber, field);
    }

    @Override
    public void Delete(String nomenclatureNumber) {
        jedis.del("literature:" + nomenclatureNumber);
    }

    @Override
    public Map<String, String> Get(String nomenclatureNumber) {
        return jedis.hgetAll("literature:" + nomenclatureNumber);

    }

    @Override
    public String GetField(String nomenclatureNumber, String field) {
        return jedis.hget("literature:" + nomenclatureNumber, field);

    }

    @Override
    public Set<String> GetAllKeys(String nomenclatureNumber) {
        return jedis.hkeys("literature:" + nomenclatureNumber);

    }

    @Override
    public List<String> GetAllValues(String nomenclatureNumber) {
        return jedis.hvals("literature:" + nomenclatureNumber);

    }

    @Override
    public long GetLength(String nomenclatureNumber) {
        return jedis.hlen("literature:" + nomenclatureNumber);
    }

    @Override
    public void DeleteAll() {
        Set<String> keys = jedis.keys("literature:*");
        for (String key : keys) {
            jedis.del(key);
        }
    }
}
