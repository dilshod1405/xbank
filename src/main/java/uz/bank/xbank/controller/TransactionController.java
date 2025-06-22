package uz.bank.xbank.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.bank.xbank.entity.Transaction;
import uz.bank.xbank.service.TransactionService;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/send")
    public ResponseEntity<Transaction> send(@RequestBody TransferRequest request, Principal principal) {
        return ResponseEntity.ok(
                transactionService.sendMoney(
                        request.fromAccount(),
                        request.toAccount(),
                        request.amount(),
                        request.description(),
                        principal
                )
        );
    }

    @GetMapping("/my")
    public ResponseEntity<List<Transaction>> myTx(Principal principal) {
        return ResponseEntity.ok(transactionService.myTransactions(principal));
    }

    public record TransferRequest(String fromAccount, String toAccount, BigDecimal amount, String description) {}
}
