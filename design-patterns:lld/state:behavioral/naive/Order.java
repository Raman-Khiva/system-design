
enum Status {
    PENDING,
    PAID,
    SHIPPED,
    DELIVERED,
    CANCELED
}




public class Order {
    private Status status = Status.PENDING;

    public void place() {
        if (status == Status.PENDING) {
            System.out.println("Order placed successfully!");
        } else {
            System.out.println("Cannot place order in " + status + " state.");
        }
    }

    public void pay() {
        if (status == Status.PENDING) {
            System.out.println("Payment successful!");
            status = Status.PAID;
        } else {
            System.out.println("Cannot pay in " + status + " state.");
        }
    }

    public void ship() {
        if (status == Status.PAID) {
            System.out.println("Order shipped successfully!");
            status = Status.SHIPPED;
        } else {
            System.out.println("Cannot ship in " + status + " state.");
        }
    }

    public void deliver() {
        if (status == Status.SHIPPED) {
            System.out.println("Order delivered successfully!");
            status = Status.DELIVERED;
        } else {
            System.out.println("Cannot deliver in " + status + " state.");
        }
    }

    public void cancel() {
        if (status == Status.PENDING || status == Status.PAID) {
            System.out.println("Order canceled successfully!");
            status = Status.CANCELED;
        } else {
            System.out.println("Cannot cancel in " + status + " state.");
        }
    }
}
