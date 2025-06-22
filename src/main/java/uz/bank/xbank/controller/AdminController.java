package uz.bank.xbank.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.bank.xbank.entity.Account;
import uz.bank.xbank.entity.Transaction;
import uz.bank.xbank.entity.User;
import uz.bank.xbank.repository.AccountRepository;
import uz.bank.xbank.repository.TransactionRepository;
import uz.bank.xbank.repository.UserRepository;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class AdminController {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    // 1. Barcha foydalanuvchilar
    @GetMapping("/users")
    public ResponseEntity<List<User>> allUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    // 2. Foydalanuvchini bloklash
    @PutMapping("/users/{id}/block")
    public ResponseEntity<?> blockUser(@PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setEnabled(false);
        userRepository.save(user);
        return ResponseEntity.ok("User blocked");
    }

    // 3. Foydalanuvchini o‘chirish
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok("User deleted");
    }

    // 4. Barcha hisob raqamlar
    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> allAccounts() {
        return ResponseEntity.ok(accountRepository.findAll());
    }

    // 5. Balansni o‘zgartirish
    @PutMapping("/accounts/{id}/balance")
    public ResponseEntity<?> updateBalance(@PathVariable Long id, @RequestBody BalanceRequest req) {
        Account account = accountRepository.findById(id).orElseThrow();
        account.setBalance(req.balance());
        accountRepository.save(account);
        return ResponseEntity.ok("Balance updated");
    }

    // 6. Barcha transactionlar
    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> allTransactions() {
        return ResponseEntity.ok(transactionRepository.findAll());
    }

    public record BalanceRequest(BigDecimal balance) {}
}
