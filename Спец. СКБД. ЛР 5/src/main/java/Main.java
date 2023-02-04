import redis.clients.jedis.Jedis;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author artem
 * @version 1.0
 * @project Спец. СКБД. ЛР 5
 * @date 04.05.2022 15:46
 * @class Main
 */
public class Main {
    public static void main(String[] args) {
        var jedis = new Jedis("http://localhost:6379");
        jedis.select(1);
        String repit = "";

        do {
            var scanner = new Scanner(System.in);
            String task = "";
            repit = "";

            System.out.println("Which collection:\n 1 - Employees \n 2 - Libraries \n 3 - Literature \n 4 - NewlyAddedBook \n 5 - Readers \n 6 - WrittenOffBooks");
            while (!Arrays.asList("1", "2", "3", "4", "5", "6", "7").contains(task)) {
                System.out.print("Answer(number): ");
                task = scanner.nextLine();
            }
            switch (task) {
                case "1":
                    EmpCrud(jedis);
                    break;
                case "2":
                    LibrCrud(jedis);
                    break;
                case "3":
                    LitrCrud(jedis);
                    break;
                case "4":
                    NewlyCrud(jedis);
                    break;
                case "5":
                    ReadCrud(jedis);
                    break;
                case "6":
                    WrittenCrud(jedis);
                    break;
                case "7":
                    ReadListCrud(jedis);
                    break;
            }

            System.out.println();

            while (!Arrays.asList("N", "n", "Y", "y").contains(repit)) {
                System.out.print("Close app?\nAnswer(N/n Y/y): ");
                repit = scanner.nextLine();
            }
            System.out.println("\n**********************************************************************************\n");
        } while (repit.equals("N") || repit.equals("n"));
    }

    public static void EmpCrud(Jedis jedis) {
        EmployeesCrud emp = new EmployeesCrud(jedis);
        emp.Create("1032",
                "worker 1",
                "library 2");
        System.out.println(emp.Get("1032"));
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("residenceAddress", "Dormitory");
        emp.UpdateField("1032", data);
        System.out.println(emp.Get("1032"));
        emp.DeleteField("1032", "fullName");
        System.out.println(emp.Get("1032"));
        System.out.println(emp.GetField("1032", "workPlace"));
        System.out.println(emp.GetAllKeys("1032"));
        System.out.println(emp.GetAllValues("1032"));
        System.out.println(emp.GetLength("1032"));
        emp.Delete("1032");
        emp.DeleteAll();
    }

    public static void LibrCrud(Jedis jedis) {
        LibrariesCrud lbr = new LibrariesCrud(jedis);
        lbr.Create("10",
                "вул. АБВ 01",
                "2");
        System.out.println(lbr.Get("10"));
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("roomsQuantity", "6");
        lbr.UpdateField("10", data);
        System.out.println(lbr.Get("10"));
        lbr.DeleteField("10", "location");
        System.out.println(lbr.Get("10"));
        System.out.println(lbr.GetField("10", "location"));
        System.out.println(lbr.GetAllKeys("10"));
        System.out.println(lbr.GetAllValues("10"));
        System.out.println(lbr.GetLength("10"));
        lbr.Delete("10");
        lbr.DeleteAll();
    }

    public static void LitrCrud(Jedis jedis) {

    }

    public static void NewlyCrud(Jedis jedis) {

    }

    public static void ReadCrud(Jedis jedis) {
        ReadersCrud rdr = new ReadersCrud(jedis);
        rdr.Create("1033",
                "artem",
                "+380969077503",
                "Home",
                LocalDate.of(2002, 7, 18));
    }

    public static void WrittenCrud(Jedis jedis) {

    }

    public static void ReadListCrud(Jedis jedis){
        ReadersListCrud rlst = new ReadersListCrud(jedis);
        rlst.Create("1033", "000000000000000001", LocalDate.now(), LocalDate.now(), "worker 1", "worker 2");
        rlst.Push("1033", "11111111111111111 " + LocalDate.now().toString() + " " +LocalDate.now().toString() + " worker-1 worker-2");
        System.out.println(rlst.GetRange("1033", 0, -1));
        rlst.UpdateByIndex("1033", 0, "updated-value");
        System.out.println(rlst.GetRange("1033", 0, -1));
        System.out.println(rlst.Pop("1033"));
        System.out.println(rlst.GetLength("1033"));
        rlst.DeleteAll();
    }
}
