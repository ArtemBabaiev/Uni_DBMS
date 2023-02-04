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
 * @class newlyAddedBooksCrud
 */
public class NewlyAddedBooksCrud implements IHash {
    private Jedis jedis;

    public NewlyAddedBooksCrud(Jedis jedis) {
        this.jedis = jedis;
    }

    public void Create(String nomenclatureNumber, LocalDate deliveryDate, String library) {

        Map<String, String> data = new HashMap<String, String>();
        data.put("deliveryDate", deliveryDate.toString());
        data.put("library", library);
        jedis.hmset("newlyAddedBooks:" + nomenclatureNumber, data);
    }

    @Override
    public void UpdateField(String nomenclatureNumber, HashMap<String, String> newData) {
        jedis.hmset("newlyAddedBooks:" + nomenclatureNumber, newData);
    }

    @Override
    public void DeleteField(String nomenclatureNumber, String field) {
        jedis.hdel("newlyAddedBooks:" + nomenclatureNumber, field);
    }

    @Override
    public void Delete(String nomenclatureNumber) {
        jedis.del("newlyAddedBooks:" + nomenclatureNumber);
    }

    @Override
    public Map<String, String> Get(String nomenclatureNumber) {
        return jedis.hgetAll("newlyAddedBooks:" + nomenclatureNumber);

    }

    @Override
    public String GetField(String nomenclatureNumber, String field) {
        return jedis.hget("newlyAddedBooks:" + nomenclatureNumber, field);

    }

    @Override
    public Set<String> GetAllKeys(String nomenclatureNumber) {
        return jedis.hkeys("newlyAddedBooks:" + nomenclatureNumber);

    }

    @Override
    public List<String> GetAllValues(String nomenclatureNumber) {
        return jedis.hvals("newlyAddedBooks:" + nomenclatureNumber);

    }

    @Override
    public long GetLength(String nomenclatureNumber) {
        return jedis.hlen("newlyAddedBooks:" + nomenclatureNumber);
    }

    @Override
    public void DeleteAll() {
        Set<String> keys = jedis.keys("newlyAddedBooks:*");
        for (String key : keys) {
            jedis.del(key);
        }
    }
}
