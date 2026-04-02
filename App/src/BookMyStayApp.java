import java.io.*;
import java.util.*;


class RoomInventory {
    private Map<String, Integer> rooms = new LinkedHashMap<>();

    public RoomInventory() {
        rooms.put("Single", 5);
        rooms.put("Double", 3);
        rooms.put("Suite", 2);
    }

    public Map<String, Integer> getRooms() {
        return rooms;
    }

    public void updateRoom(String type, int count) {
        rooms.put(type, count);
    }
}


class FilePersistenceService {

    public void saveInventory(RoomInventory inventory, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, Integer> entry : inventory.getRooms().entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue());
                writer.newLine();
            }
            System.out.println("Inventory saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving inventory.");
        }
    }

    public void loadInventory(RoomInventory inventory, String filePath) {
        File file = new File(filePath);

        if (!file.exists() || file.length() == 0) {
            System.out.println("No valid inventory data found. Starting fresh.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean validDataFound = false;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");

                if (parts.length == 2) {
                    String roomType = parts[0].trim();
                    int count = Integer.parseInt(parts[1].trim());
                    inventory.updateRoom(roomType, count);
                    validDataFound = true;
                }
            }

            if (!validDataFound) {
                System.out.println("No valid inventory data found. Starting fresh.");
            }

        } catch (Exception e) {
            System.out.println("No valid inventory data found. Starting fresh.");
        }
    }
}


public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("System Recovery");

        RoomInventory inventory = new RoomInventory();
        FilePersistenceService persistenceService = new FilePersistenceService();
        String filePath = "inventory.txt";

        persistenceService.loadInventory(inventory, filePath);

        System.out.println();
        System.out.println("Current Inventory:");
        System.out.println("Single: " + inventory.getRooms().get("Single"));
        System.out.println("Double: " + inventory.getRooms().get("Double"));
        System.out.println("Suite: " + inventory.getRooms().get("Suite"));

        persistenceService.saveInventory(inventory, filePath);
    }
}