import menu.Menu;
import menu.MenuManager;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Grocery Store Management System");

        Menu menu = new MenuManager();
        menu.run();
    }
}