package in.payhere.pos.financial.ledger.controller;

import in.payhere.pos.financial.ledger.dto.LedgerRequest;
import in.payhere.pos.financial.ledger.dto.LedgerResponse;
import in.payhere.pos.financial.ledger.service.LedgerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ledgers")
public class LedgerController {

    private final LedgerService ledgerService;

    @GetMapping
    public ResponseEntity<List<LedgerResponse>> findAll(Authentication authentication) {
        return ResponseEntity.ok(ledgerService.findAll(authentication));
    }

    @PostMapping
    public ResponseEntity<LedgerResponse> save(@RequestBody LedgerRequest createRequest, Authentication authentication) {
        LedgerResponse save = ledgerService.save(createRequest, authentication);
        return ResponseEntity
                .created(URI.create("/ledgers/" + save.getId()))
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LedgerResponse> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(ledgerService.findOne(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LedgerResponse> updateLedger(@PathVariable Long id, @RequestBody LedgerRequest updateRequest) {
        ledgerService.updateLedger(id, updateRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeLedger(@PathVariable Long id) {
        ledgerService.removeLedger(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> restoreLedger(@PathVariable Long id) {
        ledgerService.restoreLedger(id);
        return ResponseEntity.noContent().build();
    }

}
