package xyz.pavanhegde.expenseservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.pavanhegde.expenseservice.dao.ExpenseDAO;
import xyz.pavanhegde.expenseservice.dto.ExpenseRequestDTO;
import xyz.pavanhegde.expenseservice.dto.ExpenseResponseDTO;
import xyz.pavanhegde.expenseservice.model.Expense;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExpenseService {
    private final ExpenseDAO expenseDAO;

    // Get all Expenses
    public List<ExpenseResponseDTO> getAllExpenses() {
        log.info("expenseService::getAllExpenses");
        return expenseDAO.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    // Get expense by ID
    public Optional<ExpenseResponseDTO> getExpenseById(Integer id) {
        log.info("expenseService::getExpenseById ID={}", id);
        return expenseDAO.findById(id)
                .map(this::mapToResponseDTO);
    }

    // Create or update an expense
    public ExpenseResponseDTO saveExpense(ExpenseRequestDTO expenseRequestDTO) {
        log.info("expenseService::saveExpense {}", expenseRequestDTO);
        Expense expense = mapToEntity(expenseRequestDTO);
        log.info("expenseService::saveExpense expenses to be saved {}", expense);
        return mapToResponseDTO(expenseDAO.saveExpense(expense));
    }

    // Update an expense
    public Optional<ExpenseResponseDTO> updateExpense(Integer id, ExpenseRequestDTO expenseRequestDTO) {
        log.info("expenseService::updateExpense ID={} data={}", id, expenseRequestDTO);
        return expenseDAO.findById(id)
                .map(existingexpense -> {
                    existingexpense.setName(expenseRequestDTO.name());
                    existingexpense.setDescription(expenseRequestDTO.description());
                    existingexpense.setAmount(expenseRequestDTO.amount());
                    existingexpense.setCategory(expenseRequestDTO.category());
                    Expense updatedexpense = expenseDAO.updateExpense(existingexpense); // Call explicit update method
                    log.info("expenseService::updateExpense Updated expense with ID: {}", id);
                    return mapToResponseDTO(updatedexpense);
                });
    }

    // Delete an expense
    public void deleteExpense(Integer id) {
        log.info("expenseService::deleteExpense ID={}", id);
        expenseDAO.deleteById(id);
        log.info("expenseService::deleteExpense delete successful ID={}", id);
    }

    // Mapping expense entity to expenseResponseDTO
    private ExpenseResponseDTO mapToResponseDTO(Expense expense) {
        return new ExpenseResponseDTO(
                expense.getId(),
                expense.getName(),
                expense.getDescription(),
                expense.getCategory(),
                expense.getAmount(),
                expense.getCreatedAt(),
                expense.getUpdatedAt()
        );
    }

    // Mapping expenseRequestDTO to expense entity
    private Expense mapToEntity(ExpenseRequestDTO expenseRequestDTO) {
        return Expense.builder()
                .name(expenseRequestDTO.name())
                .description(expenseRequestDTO.description())
                .category(expenseRequestDTO.category())
                .amount(expenseRequestDTO.amount())
                .build();
    }
}
