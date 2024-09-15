package xyz.pavanhegde.expenseservice.dao;

import xyz.pavanhegde.expenseservice.model.Expense;

import java.util.List;
import java.util.Optional;

public interface ExpenseDAO {
    List<Expense> findAll();

    Optional<Expense> findById(Integer id);

    Expense saveExpense(Expense expense);

    void deleteById(Integer id);

    Expense updateExpense(Expense expense);
}
