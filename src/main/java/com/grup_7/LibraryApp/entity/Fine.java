
package com.grup_7.LibraryApp.entity;

import com.grup_7.LibraryApp.enums.fines.FineType;
import jakarta.persistence.*;
import java.time.LocalDate;

    @Entity
    @Table(name = "fines")
    public class Fine {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "fine_id")
        private int id;

        @Column(name = "amount")
        private double amount;

        @Column(name = "due_date")
        private LocalDate dueDate;

        @Column(name = "is_paid")
        private boolean isPaid = false;

        @Enumerated(EnumType.STRING)
        @Column(name = "fine_type")
        private FineType fineType;

        @ManyToOne
        @JoinColumn(name = "reservation_id")
        private Reservation reservation;


        public Fine() {}

        public Fine(int id, double amount, LocalDate dueDate, boolean isPaid, FineType fineType, Reservation reservation) {
            this.id = id;
            this.amount = amount;
            this.dueDate = dueDate;
            this.isPaid = isPaid;
            this.fineType = fineType;
            this.reservation = reservation;
        }


        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public double getAmount() { return amount; }
        public void setAmount(double amount) { this.amount = amount; }

        public LocalDate getDueDate() { return dueDate; }
        public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

        public boolean getIsPaid() { return isPaid; }
        public void setIsPaid(boolean isPaid) { this.isPaid = isPaid; }

        public FineType getFineType() { return fineType; }
        public void setFineType(FineType fineType) { this.fineType = fineType; }

        public Reservation getReservation() { return reservation; }
        public void setReservation(Reservation reservation) { this.reservation = reservation; }
    }



