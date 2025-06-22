package uz.bank.xbank.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.bank.xbank.entity.Account;
import uz.bank.xbank.entity.User;
import uz.bank.xbank.repository.AccountRepository;
import uz.bank.xbank.repository.UserRepository;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public Account createAccount(Principal principal) {
        User user = userRepository.findByEmail(principal.getName()).orElseThrow();

        Account account = Account.builder()
                .accountNumber(generateAccountNumber())
                .balance(BigDecimal.ZERO)
                .currency("UZS")
                .owner(user)
                .build();

        return accountRepository.save(account);
    }

    public List<Account> getMyAccounts(Principal principal) {
        User user = userRepository.findByEmail(principal.getName()).orElseThrow();
        return accountRepository.findAllByOwnerId(user.getId());
    }

    private String generateAccountNumber() {
        return "X" + UUID.randomUUID().toString().substring(0, 10).toUpperCase();
    }
}
