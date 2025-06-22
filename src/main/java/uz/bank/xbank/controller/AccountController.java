package uz.bank.xbank.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.bank.xbank.entity.Account;
import uz.bank.xbank.service.AccountService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(Principal principal) {
        return ResponseEntity.ok(accountService.createAccount(principal));
    }

    @GetMapping("/me")
    public ResponseEntity<List<Account>> myAccounts(Principal principal) {
        return ResponseEntity.ok(accountService.getMyAccounts(principal));
    }
}
