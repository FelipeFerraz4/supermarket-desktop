package model.people;

import java.time.LocalDate;

public class Client extends Person {
    private int amountPurchasesMade;
    private final LocalDate accountCreationDate;
    private LocalDate dateLastPurchase;

    public Client(
            String name,
            String cpf,
            LocalDate birthDate,
            String email,
            String password,
            String phone,
            LocalDate accountCreationDate,
            LocalDate lastReservationDate) {
        super(name, cpf, birthDate, email, password, phone);
        this.amountPurchasesMade = 0;
        this.accountCreationDate = accountCreationDate;
        this.dateLastPurchase = lastReservationDate;
    }

    public int getAmountPurchasesMade() {
        return amountPurchasesMade;
    }

    public void setAmountPurchasesMade(int reservationCount) {
        this.amountPurchasesMade = reservationCount;
    }

    public LocalDate getAccountCreationDate() {
        return accountCreationDate;
    }

    public LocalDate getDateLastPurchase() {
        return dateLastPurchase;
    }

    public void setDateLastPurchase(LocalDate lastReservationDate) {
        this.dateLastPurchase = lastReservationDate;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "id=" + this.getId() +
                ", name='" + this.getName() + '\'' +
                ", cpf='" + this.getCpf() + '\'' +
                ", birthDate=" + this.getBirthDate() +
                ", email='" + this.getEmail() + '\'' +
                ", password='" + this.getPassword() + '\'' +
                ", phone='" + this.getPhone() + '\'' +
                ", reservationCount=" + this.amountPurchasesMade +
                ", accountCreationDate=" + this.accountCreationDate +
                ", lastReservationDate=" + this.dateLastPurchase +
                '}';
    }
}