import com.mongodb.DBRef;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Field;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import static com.mongodb.client.model.Accumulators.push;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Projections.*;

/**
 * @author artem
 * @version 1.0
 * @project Спец. СКБД.  ЛР 3
 * @date 04.04.2022 11:46
 * @class Main
 */
public class Main {


    public static void main(String[] args) throws InterruptedException {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("libraryFund");
        TimeUnit.SECONDS.sleep(1);

        String   restart ="N";
        while (restart.equals("N") || restart.equals("n")){
            var scanner = new Scanner(System.in);
            System.out.print("Choose task's number.\nNumber: ");
            int tasksNumber;
            String subtask;
            try {
                tasksNumber = scanner.nextInt();
            }catch (Exception e){
                System.out.println("!!!INVALID INPUT!!!");
                continue;
            }
            scanner.nextLine();
            switch (tasksNumber){
                case 1:
                    System.out.print("Subtask: ");
                    subtask = scanner.nextLine();
                    Task1(subtask, database);
                    break;
                case 2:
                    System.out.print("Subtask: ");
                    subtask = scanner.nextLine();
                    Task2(subtask, database);
                    break;
            }

            while (true){
                System.out.print("Do you want to exit(Y/y , N/n)?\nAnswer : ");
                restart = scanner.nextLine();
                if (!Arrays.asList("n", "N", "Y", "y").contains(restart)){
                    System.out.println("!!!Invalid input!!!");
                }
                else{
                    break;
                }
            }

            System.out.println("\n****************************************************************************\n");

        }


    }
    public static void Task1(String number, MongoDatabase database){
        MongoCollection<Document> employees = database.getCollection("employees");
        MongoCollection<Document> libraries = database.getCollection("libraries");
        MongoCollection<Document> literature = database.getCollection("literature");
        MongoCollection<Document> literatureType = database.getCollection("literatureType");
        MongoCollection<Document> newlyAddedBooks = database.getCollection("newlyAddedBooks");
        MongoCollection<Document> readers = database.getCollection("readers");
        MongoCollection<Document> readersOcupation = database.getCollection("readersOcupation");
        MongoCollection<Document> subscriptions = database.getCollection("subscriptions");
        MongoCollection<Document> writtenOffBooks = database.getCollection("writtenOffBooks");
        switch (number){
            case "3.1":
                Document doc = new Document("_id", new ObjectId("000000000000000000000000"))
                        .append("literature", new DBRef("literature", "620d4bd5adfe14c5dc30097c"))
                        .append("deliveryDate", new Date())
                        .append("library", new DBRef("libraries", "620d48dbadfe14c5dc300942"));
                newlyAddedBooks.insertOne(doc);
                break;
            case "3.2":
                ArrayList<Document> documents = new ArrayList<>();
                documents.add(new Document("_id", new ObjectId("000000000000000000000001"))
                        .append("literature", new DBRef("literature", "620d4bd5adfe14c5dc30097f"))
                        .append("deliveryDate", new Date())
                        .append("library", new DBRef("libraries", "620d48dbadfe14c5dc300942")));
                documents.add(new Document("_id", new ObjectId("000000000000000000000002"))
                        .append("literature", new DBRef("literature", "620d4bd5adfe14c5dc300982"))
                        .append("deliveryDate", new Date())
                        .append("library", new DBRef("libraries", "620d48dbadfe14c5dc30093f")));
                newlyAddedBooks.insertMany(documents);
                break;
            case "4.1":
                literatureType.find().into(new ArrayList<>()).forEach(el -> System.out.println(el.toJson()));
            case "4.2":
                literatureType.find().projection(fields(excludeId(), include("type")))
                        .into(new ArrayList<>()).forEach(el -> System.out.println(el.toJson()));
                break;
            case "4.3":
                readersOcupation.find(new Document("ocupation", "Школяр"))
                        .into(new ArrayList<>()).forEach(el -> System.out.println(el.toJson()));
                break;
            case "4.4":
                literature.find().projection(fields(excludeId(), include("placeOfStorage.library", "placeOfStorage.shelf")))
                        .into(new ArrayList<>()).forEach(System.out::println);
                break;
                //отсюда
            case "4.5":
                //""
                Pattern p = Pattern.compile("Б+", Pattern.CASE_INSENSITIVE);
                literature.find(Filters.regex("name", p))
                        .into(new ArrayList<>()).forEach(System.out::println);
                break;
            case "4.6.a":
                subscriptions.find().limit(1)
                        .into(new ArrayList<>()).forEach(System.out::println);
                break;
            case "4.6.b":
                subscriptions.find().skip(4)
                        .into(new ArrayList<>()).forEach(System.out::println);
                break;
            case "4.6.c":
                //newlyAddedBooks.find().sort(descending("$natural"))
                newlyAddedBooks.find().sort(new BsonDocument("$natural", new BsonInt32(1)))
                        .into(new ArrayList<>()).forEach(System.out::println);
                break;
            case "4.6.d":
                subscriptions.find().projection(Projections.slice("readersList", 1)).projection(fields(excludeId(), include("readersList")))
                .into(new ArrayList<>()).forEach(System.out::println);
            case "4.7.a":
                System.out.println(writtenOffBooks.countDocuments());
                break;
            case "4.7.b":
                literature.distinct("type", BsonValue.class).into(new ArrayList<>()).forEach(System.out::println);
            case "4.8.a":
                readers.find(Filters.eq("birthYear", 1996))
                        .into(new ArrayList<>()).forEach(System.out::println);
                break;
            case "4.8.b":
                readers.find(Filters.ne("birthYear", 2009))
                        .into(new ArrayList<>()).forEach(System.out::println);
                break;
            case "4.8.c":
                readers.find(Filters.gt("birthYear", 2002))
                        .into(new ArrayList<>()).forEach(System.out::println);
                break;
            case "4.8.d":
                readers.find(Filters.lt("birthYear", 2003))
                        .into(new ArrayList<>()).forEach(System.out::println);
                break;
            case "4.8.e":
                readers.find(Filters.gte("birthYear", 2002))
                        .into(new ArrayList<>()).forEach(System.out::println);
                break;
            case "4.8.f":
                readers.find(Filters.lte("birthYear", 2002))
                        .into(new ArrayList<>()).forEach(System.out::println);
                break;
            case "4.8.g":
                readers.find(Filters.in("birthYear", new ArrayList<>(Arrays.asList(2003, 2009))))
                        .into(new ArrayList<>()).forEach(System.out::println);
                break;
            case "4.8.h":
                readers.find(Filters.nin("birthYear", new ArrayList<>(Arrays.asList(2003, 2009))))
                        .into(new ArrayList<>()).forEach(System.out::println);
                break;
            case "4.9.a":
                libraries.find(Filters.or(Filters.eq("number", 1), Filters.eq("roomsNumber", 3)))
                        .into(new ArrayList<>()).forEach(System.out::println);
                break;
            case "4.9.b":
                libraries.find(Filters.and(Filters.eq("number", 1), Filters.eq("roomsNumber", 3)))
                        .into(new ArrayList<>()).forEach(System.out::println);
                break;
            case "4.9.c":
                libraries.find(Filters.not(Filters.gte("roomsNumber", 4)))
                        .into(new ArrayList<>()).forEach(System.out::println);
                break;

            case "4.9.d":
                libraries.find(Filters.nor(Filters.gt("number", 3), Filters.gt("roomsNumber", 4)))
                        .into(new ArrayList<>()).forEach(System.out::println);
                break;
            case "4.10.a":
                readers.find(Filters.exists("class"))
                        .into(new ArrayList<>()).forEach(System.out::println);
                break;
            case "4.10.b":
                readers.find(Filters.type("birthYear", "string"))
                        .into(new ArrayList<>()).forEach(System.out::println);
                break;
            default:
                System.out.println("Task doesnt exist");
                break;

        }
    }

    public static void Task2(String number, MongoDatabase database){
        MongoCollection<Document> employees = database.getCollection("employees");
        MongoCollection<Document> libraries = database.getCollection("libraries");
        MongoCollection<Document> literature = database.getCollection("literature");
        MongoCollection<Document> literatureType = database.getCollection("literatureType");
        MongoCollection<Document> newlyAddedBooks = database.getCollection("newlyAddedBooks");
        MongoCollection<Document> readers = database.getCollection("readers");
        MongoCollection<Document> readersOcupation = database.getCollection("readersOcupation");
        MongoCollection<Document> subscriptions = database.getCollection("subscriptions");
        MongoCollection<Document> writtenOffBooks = database.getCollection("writtenOffBooks");
        switch (number){
            case "1":
                Bson lookup1 = lookup("literatureType", "type.$id", "_id", "typeRef");
                Bson unwind1 = unwind("$typeRef");
                Bson project1 = project(fields(excludeId(), include("name", "typeRef")));
                Document set1 = new Document("$set", new Document("typeRef", "$typeRef.type"));
                Bson group1 = group("$typeRef", push("names", "$name"));

                literature.aggregate(Arrays.asList(lookup1, unwind1, project1, set1, group1))
                        .into(new ArrayList<>()).forEach(System.out::println);
                break;
            case "2":
                Bson lookup2 = lookup("subscriptions", "subscription.$id", "_id", "subscription");
                Bson match2 = match(Filters.exists("subscription.readersList.acceptedBy", true));
                Bson project2 = project(fields(excludeId(), include("fullName", "subscription"),
                        Projections.computed("subscription", new Document(new Document("$arrayElemAt",
                                Arrays.asList("$subscription.readersList.acceptedBy", 0L))))));
                Bson unwind2 = unwind("$subscription");
                Bson project21 = project(fields(excludeId(), include("fullName", "subscription")));
                Bson count2 = count("subscription");
                readers.aggregate(Arrays.asList(lookup2, match2, project2, unwind2, project21, count2))
                        .into(new ArrayList<>()).forEach(System.out::println);
                break;
            case "3":
                Bson match3 = match(Filters.eq("name", "Гобіт, або туди і звідти"));
                Bson replaceWith3 = replaceWith(new Document("nomenclatureNumber", "$nomenclatureNumber")
                        .append("name", "$name")
                        .append("author", "$author")
                        .append("yearOfPublishing", "$yearOfPublishing")
                        .append("publisher", "$publisher"));                           //
                Bson addFields3 = addFields(Arrays.asList(
                        new Field("_id", new ObjectId("111111111111111111111111")),
                        new Field("dateOfWriteOff", "2022-02-01T00:00:00.000+00:00"),
                        new Field("testfield", "teststring".toUpperCase(Locale.ROOT))
                ));
                Bson project3 = project(exclude("testfield"));
                Bson merge3 = merge("writtenOffBooks");
                literature.aggregate(Arrays.asList(match3, replaceWith3, addFields3, project3, merge3))
                        .into(new ArrayList<>()).forEach(System.out::println);
                break;
            case "4":
                Bson sort4 = sort(new Document("ocupation", 1));
                Bson skip4 = skip(2);
                Bson limit4 = limit(3);
                Bson union4 = new Document("$unionWith", new Document("coll", "literatureType"));
                Bson count4 = count("quantity");
                readersOcupation.aggregate(Arrays.asList(sort4, skip4, limit4, union4, count4))
                        .into(new ArrayList<>()).forEach(System.out::println);
                break;
            case "5":
                Bson lookup5 = lookup("libraries", "workPlace.$id", "_id", "workPlace");
                Bson lookup51 = lookup("subscriptions", "_id", "readersList.givenBy.$id", "subscriptions");
                Bson project5 = project(fields(excludeId(), include("fullName"),
                        Projections.computed("workLocation", "$workPlace.location"),
                        Projections.computed("lendedBooks", "$subscriptions.readersList.book.$id")));
                Bson unwind5 = unwind("$lendedBooks");
                Bson sort5 = sort(new Document("fullName", -1));
                employees.aggregate(Arrays.asList(lookup5, lookup51, project5, unwind5, sort5))
                        .into(new ArrayList<>()).forEach(System.out::println);
                break;
            default:
                System.out.println("Task doesnt exist");
                break;
        }
    }
}
