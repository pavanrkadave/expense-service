package xyz.pavanhegde.expenseservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
@Entity
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    @NotEmpty(message = "Expense name cannot be empty")
    @Size(max = 100, message = "Expense name cannot exceed 100 characters")
    private String name;

    @Column(nullable = false, length = 255)
    @NotEmpty(message = "Description cannot be empty")
    @Size(max = 255, message = "Description cannot exceed 255 characters")
    private String description;

    @Column(length = 50)
    @Size(max = 50, message = "Category cannot exceed 50 characters")
    private String category;

    @Column(nullable = false, precision = 10, scale = 2)  // Use BigDecimal for monetary values
    @NotNull(message = "Amount cannot be null")
    @PositiveOrZero(message = "Amount must be zero or positive")
    private BigDecimal amount;  // Use BigDecimal instead of Double

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate createdAt;

    @UpdateTimestamp
    private LocalDate updatedAt;
}