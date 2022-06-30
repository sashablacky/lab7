package banana0081.lab6.server.connection;

import banana0081.lab6.Pack;
import banana0081.lab6.collection.HumanBeingCollectionManager;
import banana0081.lab6.data.*;
import banana0081.lab6.http.HTTPRequest;
import banana0081.lab6.io.IOUtils;
import banana0081.lab6.server.FileWorker;
import banana0081.lab6.server.commands.Save;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;

public class Server {
    private Connection conn;
    HumanBeingCollectionManager collectionManager;

    public Server(Connection conn) {
        this.conn = conn; this.collectionManager = new HumanBeingCollectionManager(conn);
    }

    public void run(int port) {
        ServerSocketChannel serverSocket;
        FileWorker fileWorker = new FileWorker(collectionManager);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try{File file = new File(System.getenv("FILE_PATH"));
            file.createNewFile();}
            catch (NullPointerException | IOException e) {
                e.printStackTrace();
            }
            try{
                Save save = new Save(collectionManager, conn);
                HTTPRequest httpRequest = new HTTPRequest();
                save.execute(httpRequest);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }));
        try {
            try {
                PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM HUMANBEING;"); {
                    ResultSet rs = preparedStatement.executeQuery();
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        String creator = rs.getString("creator");
                        Float x = rs.getFloat("x_coord");
                        Integer y = rs.getInt("y_coord");
                        Integer creation = rs.getInt("creation_date");
                        boolean realHero = rs.getBoolean("real_hero");
                        boolean toothpick = rs.getBoolean("toothpick");
                        Float impactSpeed = rs.getFloat("impact_speed");
                        Integer minutes = rs.getInt("minutes");
                        String weaponType = rs.getString("weapon_type");
                        String mood = rs.getString("mood");
                        Boolean coolness = rs.getBoolean("coolness");
                        WeaponType realWeaponType = null;
                        if(weaponType!=null){ realWeaponType = WeaponType.valueOf(weaponType);}
                        Mood realMood = null;
                        if(mood!=null){ realMood = Mood.valueOf(mood);}
                        HumanBeing person = new HumanBeing(name, new Coordinates(x, y.longValue()), realHero, toothpick, impactSpeed, minutes, realWeaponType, realMood, new Car(coolness));
                        person.setId(id);
                        collectionManager.add(person);
                    }
                if (collectionManager.getSize() == 0) {
                    System.out.println("Добавьте объекты с помощью команды add, после чего введите команду save для сохранения в xml!");
                } else System.out.println("Объекты из файла загружены! Добавьте объекты с помощью команды add, после чего введите команду save для сохранения в xml.");

            }
            serverSocket = ServerSocketChannel.open();
            serverSocket.bind(new InetSocketAddress(port));
            serverSocket.configureBlocking(false);
            ClientReader clientReader = new ClientReader();
            clientReader.reader(serverSocket, collectionManager, conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace();}
            } catch (IOException e) {
            IOUtils.println("Ошибка ввода/вывода!");
        } catch (ClassNotFoundException e) {
            IOUtils.println("Сериализуемый класс не наследует класс Serializable");

            }}}