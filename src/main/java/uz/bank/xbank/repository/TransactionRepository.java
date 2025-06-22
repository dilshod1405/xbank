package uz.bank.xbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.bank.xbank.entity.Transaction;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByFromAccountNumber(String fromAccountNumber);
}
