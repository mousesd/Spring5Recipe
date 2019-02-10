package net.homenet;

@SuppressWarnings("unused")
public class Customer {
    private Long id;
    private String firstName;
    private String lastName;
    private String telephone;
    private Float creditScore;

    public Customer() { }

    public Customer(Long id, String firstName, String lastName, String telephone, Float creditScore) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.creditScore = creditScore;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Float getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Float creditScore) {
        this.creditScore = creditScore;
    }

    @Override
    public String toString() {
        return "Customer{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", telephone='" + telephone + '\'' +
            ", creditScore=" + creditScore +
            '}';
    }
}
