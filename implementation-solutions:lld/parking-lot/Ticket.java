import java.util.UUID;

class Ticket {
    UUID id;
    UUID assignedSpotId;
    int spotLevel;
    String licenseNum;
    VehicleTypes vehicleType;
    Sizes spotCapicity;

    Ticket(UUID _spotId, String _licenseNum, Sizes _spotCapicity, int _spotLevel, VehicleTypes _vehicleType) {
        this.id = UUID.randomUUID();
        this.assignedSpotId = _spotId;
        this.spotLevel = _spotLevel;
        this.licenseNum = _licenseNum;
        this.spotCapicity = _spotCapicity;

        this.vehicleType = _vehicleType;
    }
}
