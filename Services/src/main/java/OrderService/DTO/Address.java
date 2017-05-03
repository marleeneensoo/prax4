package OrderService.DTO;

public class Address {

    private int addressId;
    private String Country;
    private String County;
    private String Town;
    private String Street;
    private String HouseNumber;
    private String DepartmentNumber;

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCounty() {
        return County;
    }

    public void setCounty(String county) {
        County = county;
    }

    public String getTown() {
        return Town;
    }

    public void setTown(String town) {
        Town = town;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getHouseNumber() {
        return HouseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        HouseNumber = houseNumber;
    }

    public String getDepartmentNumber() {
        return DepartmentNumber;
    }

    public void setDepartmentNumber(String departmentNumber) {
        DepartmentNumber = departmentNumber;
    }
}
