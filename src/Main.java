
import menu.Menu;

public class Main {
    public static void main(String[] args) {
        System.out.println("🛒 Welcome to Grocery Store Management System!");
        System.out.println("📊 All data is stored in PostgreSQL database");

        // Start the menu
        Menu menu = new MenuManager();
        menu.run();
    }
}