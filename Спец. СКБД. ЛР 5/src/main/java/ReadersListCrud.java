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
 * @date 04.05.2022 23:58
 * @class ReadersList
 */
public class ReadersListCrud implements IList{
    private Jedis jedis;

    public ReadersListCrud(Jedis jedis) {
        this.jedis = jedis;
    }

    public void Create( String readersId, String nomenclatureNumber, LocalDate givenAt, LocalDate acceptedAt, String givenBy, String acceptedBy){
        String data = nomenclatureNumber + " " + givenAt.toString() + " " + acceptedAt.toString() + givenBy + acceptedBy;
        
        jedis.rpush("readers:"+readersId + ":readersList", data);
    }

    @Override
    public List<String> GetRange(String readersId, int start, int stop) {

        return jedis.lrange("readers:"+readersId + ":readersList", start, stop);
    }

    @Override
    public void Push(String readersId, String value) {
        jedis.rpush("readers:"+readersId + ":readersList", value);
    }

    @Override
    public String GetByIndex(String readersId, int index) {
        return jedis.lindex("readers:"+readersId + ":readersList", index);
    }

    @Override
    public long GetLength(String readersId) {
        return jedis.llen("readers:"+readersId + ":readersList");
    }

    @Override
    public String Pop(String readersId) {
        return jedis.rpop("readers:"+readersId + ":readersList");
    }

    @Override
    public void UpdateByIndex(String readersId, int index, String newValue) {
        jedis.lset("readers:"+readersId + ":readersList", index, newValue);
    }

    @Override
    public void Delete(String readersId) {
        jedis.del("readers:"+readersId + ":readersList");
    }

    @Override
    public void DeleteAll() {
        Set<String> keys = jedis.keys("*:readersList");
        for (String key : keys) {
            jedis.del(key);
        }
    }
}
