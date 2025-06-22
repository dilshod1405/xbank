package uz.bank.xbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.bank.xbank.entity.Account;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByOwnerId(Long ownerId);
}
