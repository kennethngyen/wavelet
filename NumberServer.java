import java.io.IOException;
import java.net.URI;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    int num = 0;

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            //if path ends in "/", print Kenneth's Number: (string format)
            return String.format("Kenneth's Number: %d", num);
        } else if (url.getPath().equals("/increment")) {
            //if path ends in "/increment"
            num += 1;
            return String.format("Number incremented!");
        } else {
            System.out.println("Path: " + url.getPath());
            //if path ends in "/add?count=X" where X is a number, increase
            //count by X and print the following statement
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                //"add?count=X"
                if (parameters[0].equals("count")) {
                    //grab the number after query
                    num += Integer.parseInt(parameters[1]);
                    return String.format("Number increased by %s! It's now %d", parameters[1], num);
                }
            }
            return "404 Not Found!";
        }
    }
}

class NumberServer {
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
