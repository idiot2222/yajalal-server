package me.bogeun.yajalal.repository.team;

import com.querydsl.jpa.impl.JPAQueryFactory;
import me.bogeun.yajalal.entity.league.League;
import me.bogeun.yajalal.entity.league.Team;

import javax.persistence.EntityManager;
import java.util.List;

import static me.bogeun.yajalal.entity.league.QTeam.team;
import static me.bogeun.yajalal.entity.match.QMatch.match;


public class TeamRepositoryImpl implements TeamDynamicRepository{

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;

    public TeamRepositoryImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
        this.entityManager = em;
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

    @Override
    public Integer findWinCount(Team t) {
        return jpaQueryFactory
                .select(match.winningTeam.count())
                .from(match)
                .where(match.winningTeam.eq(t)
                        .and(match.winScore.gt(match.loseScore)))
                .fetchOne().intValue();
    }

    @Override
    public Integer findLoseCount(Team t) {
        return jpaQueryFactory
                .select(match.losingTeam.count())
                .from(match)
                .where(match.losingTeam.eq(t)
                        .and(match.winScore.gt(match.loseScore)))
                .fetchOne().intValue();
    }

    @Override
    public Integer findDrawCount(Team t) {
        return jpaQueryFactory
                .select(match.count())
                .from(match)
                .where(match.winningTeam.eq(t)
                        .or(match.losingTeam.eq(t))
                        .and(match.winScore.eq(match.loseScore)))
                .fetchOne().intValue();
    }

}
