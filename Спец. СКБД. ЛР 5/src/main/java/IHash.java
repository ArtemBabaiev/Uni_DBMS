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
 * @date 04.05.2022 21:37
 * @class IHash
 */
public interface IHash {

    public void UpdateField(String id, HashMap<String, String> newData);
    public void DeleteField(String id, String field);

    public void Delete(String id);
    public Map<String, String> Get(String id);

    public String GetField(String id, String field);


    public Set<String> GetAllKeys(String id);
    public List<String> GetAllValues(String id);

    public long GetLength(String id);

    public void DeleteAll();

}
