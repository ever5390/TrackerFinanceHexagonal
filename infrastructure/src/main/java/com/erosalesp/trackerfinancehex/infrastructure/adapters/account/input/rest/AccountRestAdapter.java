package com.erosalesp.trackerfinancehex.infrastructure.adapters.account.input.rest;

import com.erosalesp.trackerfinancehex.application.account.ports.input.AccountServicePort;
import com.erosalesp.trackerfinancehex.infrastructure.adapters.account.input.mapper.AccountRestMapper;
import com.erosalesp.trackerfinancehex.infrastructure.adapters.account.input.model.request.AccountCreateRequest;
import com.erosalesp.trackerfinancehex.infrastructure.adapters.account.input.model.response.AccountResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountRestAdapter {

    private final AccountServicePort accountServicePort;
    private final AccountRestMapper accRestMapper;

    public AccountRestAdapter(AccountServicePort accountServicePort, AccountRestMapper accRestMapper) {
        this.accountServicePort = accountServicePort;
        this.accRestMapper = accRestMapper;
    }
    
    @PostMapping
    public ResponseEntity<AccountResponse> create(@Valid @RequestBody AccountCreateRequest accountCreateRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(accRestMapper.toAccountResponse(
                        accountServicePort.save(
                                accRestMapper.toAccountDomain(accountCreateRequest))));
    }

    @GetMapping
    public List<AccountResponse> findAll() {
        return accRestMapper.toAccountResponseList(accountServicePort.findAll());
    }

    @GetMapping("/{id}")
    public AccountResponse findById(@PathVariable String id) {
        return accRestMapper.toAccountResponse(
                accountServicePort.findById(id));
    }

    @PutMapping("/{id}")
    public AccountResponse update(@PathVariable String id, @Valid @RequestBody AccountCreateRequest accountCreateRequest) {

        accountServicePort.update(
                id, accRestMapper.toAccountDomain(accountCreateRequest));

        return accRestMapper.toAccountResponse(
                accountServicePort.update(
                    id, accRestMapper.toAccountDomain(accountCreateRequest)));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        accountServicePort.deleteById(id);
    }

    @GetMapping("/greeting")
    public String greetings() {
        return "Hello World.. ";
    }
}
