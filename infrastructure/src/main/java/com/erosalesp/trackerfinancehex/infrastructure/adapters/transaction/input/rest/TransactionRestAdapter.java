package com.erosalesp.trackerfinancehex.infrastructure.adapters.transaction.input.rest;


import com.erosalesp.trackerfinancehex.application.transaction.ports.input.TransactionServicePort;
import com.erosalesp.trackerfinancehex.infrastructure.adapters.transaction.input.mapper.TransactionRestMapper;
import com.erosalesp.trackerfinancehex.infrastructure.adapters.transaction.input.model.request.TransactionCreateRequest;
import com.erosalesp.trackerfinancehex.infrastructure.adapters.transaction.input.model.response.TransactionResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionRestAdapter {

    private final TransactionServicePort transactionServicePort;
    private final TransactionRestMapper txRestMapper;

    public TransactionRestAdapter(TransactionServicePort transactionServicePort, TransactionRestMapper txRestMapper) {
        this.transactionServicePort = transactionServicePort;
        this.txRestMapper = txRestMapper;
    }
    
    @PostMapping
    public ResponseEntity<TransactionResponse> create(@Valid  @RequestBody TransactionCreateRequest transactionCreateRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(txRestMapper.toTransactionResponse(
                        transactionServicePort.save(
                                txRestMapper.toTransactionDomain(transactionCreateRequest))));
    }

    @GetMapping
    public List<TransactionResponse> findAll() {
        return txRestMapper.toTransactionResponseList(transactionServicePort.findAll());
    }

    @GetMapping("/{id}")
    public TransactionResponse findById(@PathVariable String id) {
        return txRestMapper.toTransactionResponse(
                transactionServicePort.findById(id));
    }

    @PutMapping("/{id}")
    public TransactionResponse update(@PathVariable String id, @Valid @RequestBody TransactionCreateRequest transactionCreateRequest) {

        return txRestMapper.toTransactionResponse(
                transactionServicePort.update(
                    id, txRestMapper.toTransactionDomain(transactionCreateRequest)));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        transactionServicePort.deleteById(id);
    }

    @GetMapping("/greeting")
    public String greetings() {
        return "Hello World.. ";
    }
}
