package com.parasol.Main.repository;

import com.parasol.Main.eenum.MethodType;
import com.parasol.Main.entity.Account;
import com.parasol.Main.entity.ApiKey;
import com.parasol.Main.entity.QAccount;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    private QAccount qAccount = QAccount.account;

    public Account getAccountCnt(MethodType method, String permitEndpoint, ApiKey clientId){
        Account account = jpaQueryFactory.select(qAccount).from(qAccount)
                .where(qAccount.method.eq(method)
                        .and(qAccount.clientId.eq(clientId))
                        .and(qAccount.permitEndpoint.eq(permitEndpoint))).fetchOne();

        return account;
    }
}
