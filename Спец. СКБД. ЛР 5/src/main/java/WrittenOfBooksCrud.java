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
 * @date 04.05.2022 15:48
 * @class WrittenOfBooksCrud
 */
public class WrittenOfBooksCrud implements IHash{

    private Jedis jedis;

    public WrittenOfBooksCrud(Jedis jedis) {
        this.jedis = jedis;
    }

    public void Create(String nomenclatureNumber, String name, String author, String yearOfPublishing ,
                       String publisher, LocalDate dateOfWrittenOff) {

        Map<String, String> data = new HashMap<String, String>();
        data.put("name", name);
        data.put("author", author);
        data.put("yearOfPublishing", yearOfPublishing);
        data.put("publisher", publisher);
        data.put("dateOfWrittenOff", dateOfWrittenOff.toString());
        jedis.hmset("writtenOffBooks:" + nomenclatureNumber, data);
    }

    @Override
    public void UpdateField(String nomenclatureNumber, HashMap<String, String> newData) {
        jedis.hmset("writtenOffBooks:" + nomenclatureNumber, newData);
    }

    @Override
    public void DeleteField(String nomenclatureNumber, String field) {
        jedis.hdel("writtenOffBooks:" + nomenclatureNumber, field);
    }

    @Override
    public void Delete(String nomenclatureNumber) {
        jedis.del("writtenOffBooks:" + nomenclatureNumber);
    }

    @Override
    public Map<String, String> Get(String nomenclatureNumber) {
        return jedis.hgetAll("writtenOffBooks:" + nomenclatureNumber);

    }

    @Override
    public String GetField(String nomenclatureNumber, String field) {
        return jedis.hget("writtenOffBooks:" + nomenclatureNumber, field);

    }

    @Override
    public Set<String> GetAllKeys(String nomenclatureNumber) {
        return jedis.hkeys("writtenOffBooks:" + nomenclatureNumber);

    }

    @Override
    public List<String> GetAllValues(String nomenclatureNumber) {
        return jedis.hvals("writtenOffBooks:" + nomenclatureNumber);

    }

    @Override
    public long GetLength(String nomenclatureNumber) {
        return jedis.hlen("writtenOffBooks:" + nomenclatureNumber);
    }

    @Override
    public void DeleteAll() {
        Set<String> keys = jedis.keys("writtenOffBooks:*");
        for (String key : keys) {
            jedis.del(key);
        }
    }
}
