package xyz.pavanhegde.expenseservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.pavanhegde.expenseservice.dto.ExpenseRequestDTO;
import xyz.pavanhegde.expenseservice.dto.ExpenseResponseDTO;
import xyz.pavanhegde.expenseservice.service.ExpenseService;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    // Get all expenses
    @GetMapping
    public ResponseEntity<List<ExpenseResponseDTO>> getAllExpenses() {
        log.info("ExpenseController::getAllExpenses - Received request to fetch all expenses");
        return ResponseEntity.ok(expenseService.getAllExpenses());
    }

    // Get expense by ID
    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponseDTO> getExpenseById(@PathVariable Integer id) {
        log.info("ExpenseController::getExpenseById - Received request to fetch expense by ID: {}", id);
        Optional<ExpenseResponseDTO> expense = expenseService.getExpenseById(id);
        if (expense.isPresent()) {
            log.info("ExpenseController::getExpenseById - Expense found with ID: {}", id);
            return ResponseEntity.ok(expense.get());
        } else {
            log.warn("ExpenseController::getExpenseById - Expense not found with ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    // Create an expense
    @PostMapping
    public ResponseEntity<ExpenseResponseDTO> createExpense(@RequestBody ExpenseRequestDTO expenseRequestDTO) {
        log.info("ExpenseController::createExpense - Received request to create expense{}", expenseRequestDTO);
        return ResponseEntity.status(CREATED).body(expenseService.saveExpense(expenseRequestDTO));
    }

    // Update expense by ID
    @PutMapping("/{id}")
    public ResponseEntity<ExpenseResponseDTO> updateExpense(@PathVariable Integer id, @RequestBody ExpenseRequestDTO expenseRequestDTO) {
        log.info("ExpenseController::updateExpense - Received request to update expense with ID: {}", id);
        Optional<ExpenseResponseDTO> updatedExpense = expenseService.updateExpense(id, expenseRequestDTO);

        if (updatedExpense.isPresent()) {
            log.info("ExpenseController::updateExpense - Expense with ID: {} updated successfully", id);
            return ResponseEntity.ok(updatedExpense.get());
        } else {
            log.warn("ExpenseController::updateExpense - Expense not found with ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    // Delete expense by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Integer id) {
        log.info("ExpenseController::deleteExpense - Received request to delete expense by ID: {}", id);
        expenseService.deleteExpense(id);
        log.info("ExpenseController::deleteExpense - Expense with ID: {} deleted successfully", id);
        return ResponseEntity.noContent().build();
    }
}
