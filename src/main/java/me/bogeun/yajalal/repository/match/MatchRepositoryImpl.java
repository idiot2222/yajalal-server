package me.bogeun.yajalal.repository.match;

import com.querydsl.jpa.impl.JPAQueryFactory;
import me.bogeun.yajalal.entity.league.Team;
import me.bogeun.yajalal.entity.match.Match;
import me.bogeun.yajalal.entity.match.QMatch;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.Optional;

import static me.bogeun.yajalal.entity.match.QMatch.match;

public class MatchRepositoryImpl implements MatchDynamicRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public MatchRepositoryImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<Match> findMatch(Team winningTeam, Team losingTeam, LocalDate matchDate) {
        Match match = jpaQueryFactory
                .selectFrom(QMatch.match)
                .where(QMatch.match.winningTeam.eq(winningTeam)
                        .and(QMatch.match.losingTeam.eq(losingTeam))
                        .and(QMatch.match.matchDate.eq(matchDate)))
                .fetchOne();

        return Optional.ofNullable(match);
    }
}
