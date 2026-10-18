public class Order {
    private State currentState = new PendingState();

    public void place() {
        currentState.place(this);
    }

    public void pay() {
        currentState.pay(this);
    }

    public void ship() {
        currentState.ship(this);
    }

    public void deliver() {
        currentState.deliver(this);
    }

    public void cancel() {
        currentState.cancel(this);
    }

    public void setState(State state) {
        this.currentState = state;
    }
}
