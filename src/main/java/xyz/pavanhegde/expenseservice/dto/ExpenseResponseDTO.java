package xyz.pavanhegde.expenseservice.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpenseResponseDTO(Integer id, String name, String description, String category, BigDecimal amount,
                                 LocalDate createdAt, LocalDate updatedAt) {
}
