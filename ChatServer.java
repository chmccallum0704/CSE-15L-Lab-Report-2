import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

class ChatHandler implements URLHandler {
  List<String> lines;
  String path;
  ChatHandler(String path) throws IOException {
    this.path = path;
    this.lines = Files.readAllLines(Paths.get(path));
  }
  public String handleRequest(URI url) throws IOException {
    String query = url.getQuery();
    if(url.getPath().equals("/add-message")) {
      if(query.startsWith("s=")) {
        String userToAdd = query.split("=")[2];
        String messagetoAdd = query.split("=")[1].split("&")[0];
        this.lines.addAll(userToAdd, messageToAdd);
        return String.format("%s: %s\n", userToAdd, messageToAdd);
      }
      else {
        return "/add message requires a query parameter s\n";
      }
    }

class ChatServer {
  public static void main(String[] args) throws IOException {
    if(args.length == 0){
      System.out.println("Missing port number! Try any number between 1024 to 49151");
      return;
    }
    if(args.length == 1){
      System.out.println("Missing file path! Give a path to a text file as the second argument.");
      return;
    }

    int port = Integer.parseInt(args[0]);

    Server.start(port, new ChatHandler(args[1]));
  }
}
