package me.bogeun.yajalal.repository.player;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

public class PlayerRepositoryImpl implements PlayerDynamicRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public PlayerRepositoryImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

}
