package uz.bank.xbank.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.bank.xbank.entity.Account;
import uz.bank.xbank.entity.Transaction;
import uz.bank.xbank.repository.AccountRepository;
import uz.bank.xbank.repository.TransactionRepository;
import uz.bank.xbank.repository.UserRepository;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    @Transactional
    public Transaction sendMoney(String fromAccountNumber, String toAccountNumber, BigDecimal amount, String desc, Principal principal) {
        Account from = accountRepository.findAllByOwnerId(userRepository.findByEmail(principal.getName()).orElseThrow().getId())
                .stream().filter(a -> a.getAccountNumber().equals(fromAccountNumber)).findFirst()
                .orElseThrow(() -> new RuntimeException("Your account not found"));

        Account to = accountRepository.findAll().stream()
                .filter(a -> a.getAccountNumber().equals(toAccountNumber))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Recipient account not found"));

        if (from.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Not enough balance");
        }

        from.setBalance(from.getBalance().subtract(amount));
        to.setBalance(to.getBalance().add(amount));

        accountRepository.save(from);
        accountRepository.save(to);

        Transaction tx = Transaction.builder()
                .fromAccountNumber(fromAccountNumber)
                .toAccountNumber(toAccountNumber)
                .amount(amount)
                .description(desc)
                .timestamp(LocalDateTime.now())
                .status("SUCCESS")
                .build();

        return transactionRepository.save(tx);
    }

    public List<Transaction> myTransactions(Principal principal) {
        Long userId = userRepository.findByEmail(principal.getName()).orElseThrow().getId();
        List<Account> accounts = accountRepository.findAllByOwnerId(userId);
        return transactionRepository.findAllByFromAccountNumber(accounts.get(0).getAccountNumber()); // oddiy variant
    }
}
