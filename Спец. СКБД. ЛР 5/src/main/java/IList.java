import java.util.List;

/**
 * @author artem
 * @version 1.0
 * @project Спец. СКБД. ЛР 5
 * @date 04.05.2022 21:38
 * @class IList
 */
public interface IList {
    public List<String> GetRange(String reader, int start, int stop);
    public void Push(String reader, String value);
    public String GetByIndex(String reader, int index);
    public long GetLength(String reader);
    public String Pop(String reader);
    public void UpdateByIndex(String reader, int index, String newValue);
    public void Delete(String reader);
    public void DeleteAll();
}
