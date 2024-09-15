package xyz.pavanhegde.expenseservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.pavanhegde.expenseservice.model.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
}
