import java.io.IOException;
import java.net.URI;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.

    String returnStr="";

    public String handleRequest(URI url) {
        if (url.getPath().equals("/add-message")) {
            //if path ends in "/search"
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                String search = parameters[1];
                returnStr+=search+"\n"; 
            }          
            return returnStr;
    }
        else{
            return "404 Not Found!";
        }
    }

}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            //if no port provided, force to provide a port by ending proram
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }
        //grab port
        int port = Integer.parseInt(args[0]);
        //start server with provided port
        Server.start(port, new Handler());
    }
}
