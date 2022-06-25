package banana0081.lab6.client;

public class ClientMain {
    public static void main(String[] args) {
        Client client = new Client();
        try {
            client.run(8080);
        } catch(Exception e){
            System.out.println("это я тоже предусмотрел (или нет)");
        }
    }
}
