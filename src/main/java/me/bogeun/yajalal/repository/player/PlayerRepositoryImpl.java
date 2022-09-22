package me.bogeun.yajalal.repository.player;

import com.querydsl.jpa.impl.JPAQueryFactory;
import me.bogeun.yajalal.entity.Position;

import javax.persistence.EntityManager;
import java.util.List;

import static me.bogeun.yajalal.entity.QPlayer.player;

public class PlayerRepositoryImpl implements PlayerDynamicRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public PlayerRepositoryImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }


    @Override
    public List<Position> findAllSubPositionsByUserId(Long userId) {
        return jpaQueryFactory
                .select(player.subPositions)
                .from(player)
                .where(player.account.id.eq(userId))
                .fetchOne();
    }
}
