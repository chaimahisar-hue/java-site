import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));
        Javalin app = Javalin.create().start(port);

        app.get("/", ctx -> {
            ctx.html("<h1>Hello from Java!</h1><p>My website is live.</p>");
        });
    }
}
