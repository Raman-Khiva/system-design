import java.util.UUID;

class Ticket{
    UUID id;
    UUID assignedSpotId;
    int spotLevel;
    UUID vehicleId;
    VehicleTypes vehicleType;

    Ticket(UUID _spotId, UUID _vehicleId, int _spotLevel, VehicleTypes _vehicleType){
        this.id = UUID.randomUUID();
        this.assignedSpotId = _spotId;
        this.spotLevel = _spotLevel;
        this.vehicleId = _vehicleId;
        this.vehicleType = _vehicleType;
    }
}
