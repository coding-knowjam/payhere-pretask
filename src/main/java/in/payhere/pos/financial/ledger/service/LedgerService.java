package in.payhere.pos.financial.ledger.service;

import in.payhere.pos.financial.ledger.domain.Ledger;
import in.payhere.pos.financial.ledger.domain.LedgerStatus;
import in.payhere.pos.financial.ledger.dto.LedgerRequest;
import in.payhere.pos.financial.ledger.dto.LedgerResponse;
import in.payhere.pos.financial.ledger.repository.LedgerRepository;
import in.payhere.pos.financial.security.domain.PrincipalDetails;
import in.payhere.pos.financial.user.domain.User;
import in.payhere.pos.financial.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class LedgerService {

    private final LedgerRepository ledgerRepository;
    private final UserRepository userRepository;

    public LedgerResponse save (LedgerRequest createRequest, Authentication authentication) {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        User findUser = userRepository.findById(principal.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자 입니다."));
        Ledger saveLedger = ledgerRepository.save(createRequest.toCreateLedger(findUser));
        return LedgerResponse.of(saveLedger);
    }

    public void updateLedger(Long id, LedgerRequest updateRequest) {
        Ledger ledger = ledgerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("가계부 내역이 존재하지 않습니다."));
        ledger.update(updateRequest.toUpdateLedger());
    }

    public void removeLedger(Long id) {
        Ledger ledger = ledgerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("가계부 내역이 존재하지 않습니다."));
        ledger.remove();
    }

    public void restoreLedger(Long id) {
        Ledger ledger = ledgerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("가계부 내역이 존재하지 않습니다."));
        ledger.restore();
    }

    @Transactional(readOnly = true)
    public List<LedgerResponse> findAll(Authentication authentication) {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        User findUser = userRepository.findById(principal.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자 입니다."));

        return ledgerRepository.findByUser(findUser).stream()
                .map(ledger -> LedgerResponse.of(ledger))
                .collect(Collectors.toUnmodifiableList());
    }

    @Transactional(readOnly = true)
    public LedgerResponse findOne(Long id) {
        Ledger ledger = ledgerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("가계부 내역이 존재하지 않습니다."));
        return LedgerResponse.of(ledger);
    }
}
