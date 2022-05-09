package com.parasol.Main.repository;

import com.parasol.Main.eenum.MethodType;
import com.parasol.Main.entity.Account;
import com.parasol.Main.entity.ApiKey;
import com.parasol.Main.entity.Client;
import com.parasol.Main.entity.QClient;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClientRepositorySupport {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    private QClient qClient = QClient.client;

    public Client getClientCnt(MethodType method, String permitEndpoint, ApiKey clientId){
        Client client = jpaQueryFactory.select(qClient).from(qClient)
                .where(qClient.method.eq(method)
                        .and(qClient.clientId.eq(clientId))
                        .and(qClient.permitEndpoint.eq(permitEndpoint))).fetchOne();

        return client;
    }
}
