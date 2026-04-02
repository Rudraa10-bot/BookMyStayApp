import java.util.*;

// Custom Exception
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Room Inventory
class RoomInventory {
    private Map<String, Integer> roomAvailability;

    public RoomInventory() {
        roomAvailability = new HashMap<>();
        roomAvailability.put("Single", 5);
        roomAvailability.put("Double", 3);
        roomAvailability.put("Suite", 2);
    }

    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }
}

// Booking Queue
class BookingRequestQueue {
    private Queue<String> queue = new LinkedList<>();

    public void addRequest(String request) {
        queue.offer(request);
    }
}

// Validator Class
class ReservationValidator {

    public void validate(String guestName, String roomType, RoomInventory inventory)
            throws InvalidBookingException {

        // Validate guest name
        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty");
        }

        // Validate room type (CASE SENSITIVE)
        Map<String, Integer> rooms = inventory.getRoomAvailability();

        if (!rooms.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid room type selected.");
        }

        // Check availability
        if (rooms.get(roomType) <= 0) {
            throw new InvalidBookingException("Selected room type is not available.");
        }
    }
}

// Main Class
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Booking Validation");

        Scanner scanner = new Scanner(System.in);

        RoomInventory inventory = new RoomInventory();
        ReservationValidator validator = new ReservationValidator();
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        try {
            // Input
            System.out.print("Enter guest name: ");
            String name = scanner.nextLine();

            System.out.print("Enter room type (Single/Double/Suite): ");
            String roomType = scanner.nextLine();

            // Validation
            validator.validate(name, roomType, inventory);

            // If valid → add to queue
            bookingQueue.addRequest(name + "-" + roomType);

            System.out.println("Booking request added successfully");

        } catch (InvalidBookingException e) {
            System.out.println("Booking failed: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}