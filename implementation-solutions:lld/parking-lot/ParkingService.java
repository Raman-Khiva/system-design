
class ParkingService {

    ParkingLot plot;

    ParkingService() {
        plot = new ParkingLot();
    }

    String park(Vehicle v) {
        if (v.parked) {
            return "Vehicle is Already Parked!";
        }

        ParkingSpot freeSpot = plot.findFreeSpotBySize(v.size);

        if (freeSpot == null) {
            return "Can't Park! No parking spot is availble";
        }

        Ticket ticket = new Ticket(freeSpot.id, v.licenseNumber, freeSpot.capicity, freeSpot.level, v.type);

        freeSpot.book(ticket);
        v.park(ticket);

        return "Vehicle Parked successfully!  ticketID" + ticket.id;
    }

    String unpark(Vehicle v) {
        if (!v.parked) {
            return "vechile isn't parked";
        }

        Ticket t = v.ticket;
        ParkingSpot spot = plot.getSpot(t.assignedSpotId);
        if (spot == null) {
            return "Invalid Ticket";
        }
        spot.free();
        v.unpark();

        return "Vehicle unparked successfully!  License Number : " + v.licenseNumber;

    }
}
