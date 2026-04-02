import java.util.*;

// Reservation Class
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

// Booking Queue
class BookingRequestQueue {
    private Queue<Reservation> queue = new LinkedList<>();

    public void addRequest(Reservation r) {
        queue.offer(r);
    }

    public Reservation getNextRequest() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

// Room Inventory
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

// Allocation Service
class RoomAllocationService {
    private Map<String, Integer> bookingCounter = new HashMap<>();

    public String allocateRoom(Reservation r, RoomInventory inventory) {
        String type = r.getRoomType();
        Map<String, Integer> rooms = inventory.getRooms();

        if (rooms.getOrDefault(type, 0) > 0) {
            inventory.updateRoom(type, rooms.get(type) - 1);

            int count = bookingCounter.getOrDefault(type, 0) + 1;
            bookingCounter.put(type, count);

            String roomId = type + "-" + count;

            System.out.println("Booking confirmed for Guest: " +
                    r.getGuestName() + ", Room ID: " + roomId);

            return roomId;
        } else {
            System.out.println("Booking failed for Guest: " +
                    r.getGuestName() + ", No " + type + " rooms available");
            return null;
        }
    }
}

// Concurrent Processor
class ConcurrentBookingProcessor implements Runnable {

    private BookingRequestQueue bookingQueue;
    private RoomInventory inventory;
    private RoomAllocationService allocationService;

    public ConcurrentBookingProcessor(
            BookingRequestQueue bookingQueue,
            RoomInventory inventory,
            RoomAllocationService allocationService) {
        this.bookingQueue = bookingQueue;
        this.inventory = inventory;
        this.allocationService = allocationService;
    }

    @Override
    public void run() {
        while (true) {
            Reservation reservation;

            synchronized (bookingQueue) {
                if (bookingQueue.isEmpty()) {
                    break;
                }
                reservation = bookingQueue.getNextRequest();
            }

            synchronized (inventory) {
                allocationService.allocateRoom(reservation, inventory);
            }
        }
    }
}

// Main Class
public class BookMyStayApp {
    public static void main(String[] args) {

        System.out.println("Concurrent Booking Simulation");

        BookingRequestQueue bookingQueue = new BookingRequestQueue();
        RoomInventory inventory = new RoomInventory();
        RoomAllocationService allocationService = new RoomAllocationService();

        // Add booking requests
        bookingQueue.addRequest(new Reservation("Abhi", "Single"));
        bookingQueue.addRequest(new Reservation("Vanmathi", "Double"));
        bookingQueue.addRequest(new Reservation("Kural", "Suite"));
        bookingQueue.addRequest(new Reservation("Subha", "Single"));

        // create booking processor tasks
        Thread t1 = new Thread(
                new ConcurrentBookingProcessor(
                        bookingQueue, inventory, allocationService
                )
        );

        Thread t2 = new Thread(
                new ConcurrentBookingProcessor(
                        bookingQueue, inventory, allocationService
                )
        );

        // start concurrent processing
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread execution interrupted.");
        }

        System.out.println();
        System.out.println("Remaining Inventory:");
        System.out.println("Single: " + inventory.getRooms().get("Single"));
        System.out.println("Double: " + inventory.getRooms().get("Double"));
        System.out.println("Suite: " + inventory.getRooms().get("Suite"));
    }
}