package model.people;

import java.time.LocalDate;

public class Guest extends Person {
    private int reservationCount;
    private final LocalDate accountCreationDate;
    private LocalDate lastReservationDate;

    public Guest(
            String name,
            String cpf,
            LocalDate birthDate,
            String email,
            String password,
            String phone,
            LocalDate accountCreationDate,
            LocalDate lastReservationDate) {
        super(name, cpf, birthDate, email, password, phone);
        this.reservationCount = 0;
        this.accountCreationDate = accountCreationDate;
        this.lastReservationDate = lastReservationDate;
    }

    public int getReservationCount() {
        return reservationCount;
    }

    public void setReservationCount(int reservationCount) {
        this.reservationCount = reservationCount;
    }

    public LocalDate getAccountCreationDate() {
        return accountCreationDate;
    }

    public LocalDate getLastReservationDate() {
        return lastReservationDate;
    }

    public void setLastReservationDate(LocalDate lastReservationDate) {
        this.lastReservationDate = lastReservationDate;
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
                ", reservationCount=" + this.reservationCount +
                ", accountCreationDate=" + this.accountCreationDate +
                ", lastReservationDate=" + this.lastReservationDate +
                '}';
    }
}