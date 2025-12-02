package se.osoco.api.model;

import java.util.List;

import se.osoco.domain.reports.IncomeStatement;

public class IncomeReport {
    private final List<Account> income;
    private final List<Account> expenses;

    private final List<Account> financial;

    public IncomeReport(IncomeStatement incomeStatement) {
        income = incomeStatement.totalIncome().accounts().stream().map(Account::new).sorted().toList();
        expenses = incomeStatement.totalOperatingExpenses().accounts().stream().map(Account::new).sorted().toList();
        financial = incomeStatement.totalFinancial().accounts().stream().map(Account::new).sorted().toList();
    }
}
