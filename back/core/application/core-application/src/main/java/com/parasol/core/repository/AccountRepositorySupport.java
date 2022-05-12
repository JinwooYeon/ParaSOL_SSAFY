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

    public List<TransactionHistory> getTransactionHistory(String accountNo){
        Account account = new Account();

        account.setId(accountNo);

        return jpaQueryFactory.select(qTransactionHistory)
                .from(qTransactionHistory)
                .where(qTransactionHistory.transactionAccount.eq(accountNo)
                        .or(qTransactionHistory.account.eq(account)))
                .join(qTransactionHistory.account, QAccount.account)
                .fetchJoin().fetch();
    }
}
