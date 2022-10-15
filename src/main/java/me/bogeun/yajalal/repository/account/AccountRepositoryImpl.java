package me.bogeun.yajalal.repository.account;

import com.querydsl.jpa.impl.JPAQueryFactory;
import me.bogeun.yajalal.entity.account.Role;

import javax.persistence.EntityManager;

import static me.bogeun.yajalal.entity.QAccount.account;

public class AccountRepositoryImpl implements AccountDynamicRepository{

    private final JPAQueryFactory jpaQueryFactory;

    public AccountRepositoryImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Role getRoleByUsername(String username) {
        return jpaQueryFactory
                .select(account.role)
                .from(account)
                .where(account.username.eq(username))
                .fetchOne();
    }

}
