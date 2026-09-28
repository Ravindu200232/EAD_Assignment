package lk.sliit.solargrid.data.remote.dto;

public class UserDto {

    public String nic;
    public String fullName;
    public String email;
    public String phone;

    /** "Prosumer", "GridOperator" or "Backoffice". */
    public String role;

    /** "Pending", "Active" or "Deactivated". */
    public String status;

    public String address;
    public String meterNumber;
    public Double solarCapacityKw;

    public String createdAt;
    public String activatedAt;
    public String deactivatedAt;
    public String lastLoginAt;

    /** The first name, used for greetings such as "Hello Kasun". */
    public String firstName() {
        if (fullName == null || fullName.trim().isEmpty()) {
            return "";
        }
        return fullName.trim().split("\s+")[0];
    }
}
