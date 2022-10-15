package me.bogeun.yajalal.repository.team;

import com.querydsl.jpa.impl.JPAQueryFactory;
import me.bogeun.yajalal.entity.league.League;
import me.bogeun.yajalal.entity.league.Team;

import javax.persistence.EntityManager;
import java.util.List;

import static me.bogeun.yajalal.entity.league.QTeam.team;


public class TeamRepositoryImpl implements TeamDynamicRepository{

    private final JPAQueryFactory jpaQueryFactory;

    public TeamRepositoryImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public League findLeagueByTeam(Team t) {
        return jpaQueryFactory
                .select(team.league)
                .from(team)
                .where(team.eq(t))
                .fetchOne();
    }

    @Override
    public List<Team> findTeamListByLeague(League league) {
        return jpaQueryFactory
                .selectFrom(team)
                .where(team.league.eq(league))
                .fetch();
    }
}
