package com.parasol.core.repository;

import com.parasol.core.entity.Account;
import com.parasol.core.entity.QAccount;
import com.parasol.core.entity.QTransactionHistory;
import com.parasol.core.entity.TransactionHistory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountRepositorySupport{
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    private QTransactionHistory qTransactionHistory = QTransactionHistory.transactionHistory;

    public List<TransactionHistory> getTransactionHistory(String accountNumber){
        return jpaQueryFactory.select(qTransactionHistory)
                .from(qTransactionHistory)
                .where(qTransactionHistory.accountNumberOpponent.eq(accountNumber)
                        .or(qTransactionHistory.account.id.eq(accountNumber)))
                .join(qTransactionHistory.account, QAccount.account)
                .fetchJoin().fetch();
    }
}
