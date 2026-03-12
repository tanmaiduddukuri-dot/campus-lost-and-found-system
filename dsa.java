import java.util.*;

class Item {
    int id;
    String name;
    String description;
    String location;
    String type;

    Item(int id, String name, String description, String location, String type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.type = type;
    }

    void display() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Description: " + description);
        System.out.println("Location: " + location);
        System.out.println("Type: " + type);
        System.out.println("---------------------------");
    }
}

public class LostFoundSystem {

    // CO2: LinkedList
    static LinkedList<Item> itemList = new LinkedList<>();

    // CO3: Queue
    static Queue<Item> processingQueue = new LinkedList<>();

    // CO3: Priority Queue
    static PriorityQueue<Item> priorityItems =
            new PriorityQueue<>((a, b) -> a.name.compareToIgnoreCase(b.name));

    // CO4: HashMap
    static HashMap<Integer, Item> itemMap = new HashMap<>();

    static Scanner sc = new Scanner(System.in);

    // ==============================
    // Methods for Web Integration
    // ==============================

    public static void addItemFromWeb(String name, String desc, String loc, String type) {

        int id = itemList.size() + 1;

        Item item = new Item(id, name, desc, loc, type);

        itemList.add(item);
        processingQueue.add(item);
        itemMap.put(id, item);

        System.out.println("Item added from Web");
    }

    public static List<Item> getItems() {
        return itemList;
    }

    // ==============================
    // Add Item
    // ==============================

    static void addItem() {

        System.out.print("Enter Item ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Item Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Description: ");
        String desc = sc.nextLine();

        System.out.print("Enter Location: ");
        String loc = sc.nextLine();

        System.out.print("Type (Lost/Found): ");
        String type = sc.nextLine();

        Item item = new Item(id, name, desc, loc, type);

        itemList.add(item);        // LinkedList
        processingQueue.add(item); // Queue
        itemMap.put(id, item);     // HashMap

        System.out.println("Item Added Successfully!");
    }

    // ==============================
    // Display Items
    // ==============================

    static void displayItems() {

        if (itemList.isEmpty()) {
            System.out.println("No items reported.");
            return;
        }

        for (Item item : itemList) {
            item.display();
        }
    }

    // ==============================
    // CO1: Linear Search
    // ==============================

    static void searchItem() {

        System.out.print("Enter item name to search: ");
        String name = sc.nextLine();

        boolean found = false;

        for (Item item : itemList) {

            if (item.name.equalsIgnoreCase(name)) {
                item.display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Item not found.");
        }
    }

    // ==============================
    // CO4: HashMap Search
    // ==============================

    static void searchById() {

        System.out.print("Enter Item ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        Item item = itemMap.get(id);

        if (item != null) {
            item.display();
        } else {
            System.out.println("Item not found.");
        }
    }

    // ==============================
    // CO3: Queue Processing
    // ==============================

    static void processNextItem() {

        if (processingQueue.isEmpty()) {
            System.out.println("No items in queue.");
            return;
        }

        Item item = processingQueue.poll();

        System.out.println("Processing Item:");
        item.display();
    }

    // ==============================
    // CO3: Priority Queue
    // ==============================

    static void addPriorityItem() {

        System.out.print("Enter Item ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Item Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Description: ");
        String desc = sc.nextLine();

        System.out.print("Enter Location: ");
        String loc = sc.nextLine();

        System.out.print("Type (Lost/Found): ");
        String type = sc.nextLine();

        Item item = new Item(id, name, desc, loc, type);

        priorityItems.add(item);

        System.out.println("Priority Item Added!");
    }

    static void processPriorityItem() {

        if (priorityItems.isEmpty()) {
            System.out.println("No priority items.");
            return;
        }

        Item item = priorityItems.poll();

        System.out.println("Processing Priority Item:");
        item.display();
    }

    // ==============================
    // CO1: Sorting
    // ==============================

    static void sortItems() {

        if (itemList.isEmpty()) {
            System.out.println("No items to sort.");
            return;
        }

        Collections.sort(itemList, new Comparator<Item>() {
            public int compare(Item a, Item b) {
                return a.name.compareToIgnoreCase(b.name);
            }
        });

        System.out.println("Items Sorted Alphabetically:");

        for (Item item : itemList) {
            item.display();
        }
    }

    // ==============================
    // Main Menu
    // ==============================

    public static void main(String[] args) {

        while (true) {

            System.out.println("\nCampus Lost and Found System");
            System.out.println("1. Add Item");
            System.out.println("2. Display All Items");
            System.out.println("3. Search Item by Name");
            System.out.println("4. Search Item by ID");
            System.out.println("5. Process Next Item (Queue)");
            System.out.println("6. Sort Items");
            System.out.println("7. Add Priority Item");
            System.out.println("8. Process Priority Item");
            System.out.println("9. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addItem();
                    break;

                case 2:
                    displayItems();
                    break;

                case 3:
                    searchItem();
                    break;

                case 4:
                    searchById();
                    break;

                case 5:
                    processNextItem();
                    break;

                case 6:
                    sortItems();
                    break;

                case 7:
                    addPriorityItem();
                    break;

                case 8:
                    processPriorityItem();
                    break;

                case 9:
                    System.exit(0);

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
