
enum VehicleTypes {
    Car,
    Bike,
    Truck,
}

enum Sizes {
    SMALL,
    MEDIUM,
    LARGE,
}

/*

*/

/*
 * ticket{
 * - id
 * - assignedspod
 * - spot level
 * - vechile id
 * - vechile type
 * }
 * 
 * vehicle{
 * - id
 * - type
 * - ticket
 * + setTicket()
 * + getTicket()
 * }
 * 
 * spot{
 * - id
 * - type
 * - level
 * - booked
 * }
 * 
 * pakringlot{
 * - spots
 * + park()
 * + unpark()
 * + getAvailble
 * }
 * 
 */
