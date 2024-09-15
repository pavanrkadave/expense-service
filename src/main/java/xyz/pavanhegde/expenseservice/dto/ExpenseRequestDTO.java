package xyz.pavanhegde.expenseservice.dto;

import java.math.BigDecimal;

public record ExpenseRequestDTO(String name, String description, String category, BigDecimal amount) {
}
