package net.homenet;

@SuppressWarnings("WeakerAccess")
public class Mail {
    private String mailId;
    private String country;
    private Double weight;

    public Mail() { }

    public Mail(String mailId, String country, Double weight) {
        this.mailId = mailId;
        this.country = country;
        this.weight = weight;
    }

    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Mail{" +
            "mailId='" + mailId + '\'' +
            ", country='" + country + '\'' +
            ", weight=" + weight +
            '}';
    }
}
