package uz.bank.xbank.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fromAccountNumber;
    private String toAccountNumber;

    private BigDecimal amount;

    private LocalDateTime timestamp;

    private String status; // SUCCESS, FAILED, etc.

    private String description;
}
