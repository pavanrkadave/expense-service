package xyz.pavanhegde.expenseservice.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import xyz.pavanhegde.expenseservice.dao.ExpenseDAO;
import xyz.pavanhegde.expenseservice.model.Expense;
import xyz.pavanhegde.expenseservice.repository.ExpenseRepository;

import java.util.List;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@Repository
public class ExpenseDAOImpl implements ExpenseDAO {

    private final ExpenseRepository expenseRepository;

    @Override
    public List<Expense> findAll() {
        log.info("ExpenseDAOImpl::findAll");
        return expenseRepository.findAll();
    }

    @Override
    public Optional<Expense> findById(Integer id) {
        log.info("ExpenseDAOImpl::findById ID={}", id);
        return expenseRepository.findById(id);
    }

    @Override
    public Expense saveExpense(Expense expense) {
        log.info("ExpenseDAOImpl::saveExpense {}", expense);
        return expenseRepository.save(expense);
    }

    @Override
    public void deleteById(Integer id) {
        log.info("ExpenseDAOImpl::deleteById ID={}", id);
        expenseRepository.deleteById(id);
    }

    @Override
    public Expense updateExpense(Expense expense) {
        log.info("ExpenseDAOImpl::updateExpense {}", expense);
        return expenseRepository.save(expense);
    }
}
