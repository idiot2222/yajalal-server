package me.bogeun.yajalal.repository.player;

import com.querydsl.jpa.impl.JPAQueryFactory;
import me.bogeun.yajalal.entity.league.League;
import me.bogeun.yajalal.entity.team.Team;
import me.bogeun.yajalal.payload.stat.PlayerStat;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

import static me.bogeun.yajalal.entity.player.QPlayer.player;


public class PlayerRepositoryImpl implements PlayerDynamicRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager em;

    public PlayerRepositoryImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
        this.em = em;
    }

    @Override
    public Team getTeamByPlayerId(Long playerId) {
        return jpaQueryFactory
                .select(player.team)
                .from(player)
                .where(player.id.eq(playerId))
                .fetchOne();
    }

    @Override
    public List<PlayerStat> getTopBatterByTeam(Long teamId, String stat, int n) {
        String jpql = String.format(
                "select p.name as name, b.%s as stat, p.backNumber " +
                "from Player p inner join Batting b on p.id = b.player " +
                "where p.team = %d " +
                "order by b.%s desc",
                stat, teamId, stat
        );

        return em.createQuery(jpql, Object[].class)
                .setMaxResults(n)
                .getResultList()
                .stream()
                .map(x -> new PlayerStat((String) x[0], (Integer) x[1], (Integer) x[2]))
                .collect(Collectors.toList());
    }

    @Override
    public List<PlayerStat> getTopPitcherByTeam(Long teamId, String stat, int n) {
        String jpql = String.format(
                "select p.name as name, pc.%s as stat, p.backNumber " +
                        "from Player p inner join Pitching pc on p.id = pc.player " +
                        "where p.team = %d " +
                        "order by pc.%s desc",
                stat, teamId, stat
        );

        return em.createQuery(jpql, Object[].class)
                .setMaxResults(n)
                .getResultList()
                .stream()
                .map(x -> new PlayerStat((String) x[0], (Integer) x[1], (Integer) x[2]))
                .collect(Collectors.toList());
    }

    @Override
    public List<PlayerStat> findTopBattersStatByLeague(League league, String stat, int count) {
        String jpql = String.format(
                "select p.name as name, b.%s as stat, p.backNumber " +
                        "from Player p inner join Batting b on p.id = b.player, Team t " +
                        "where t.league = %d and t.id = p.team " +
                        "order by b.%s desc",
                stat, league.getId(), stat
        );

        return em.createQuery(jpql, Object[].class)
                .setMaxResults(count)
                .getResultList()
                .stream()
                .map(x -> new PlayerStat((String) x[0], (Integer) x[1], (Integer) x[2]))
                .collect(Collectors.toList());
    }


    @Override
    public List<PlayerStat> findTopPitchersStatByLeague(League league, String stat, int count) {
        String jpql = String.format(
                "select p.name as name, pc.%s as stat, p.backNumber " +
                        "from Player p inner join Pitching pc on p.id = pc.player, Team t " +
                        "where t.league = %d and t.id = p.team " +
                        "order by pc.%s desc",
                stat, league.getId(), stat
        );

        return em.createQuery(jpql, Object[].class)
                .setMaxResults(count)
                .getResultList()
                .stream()
                .map(x -> new PlayerStat((String) x[0], (Integer) x[1], (Integer) x[2]))
                .collect(Collectors.toList());
    }
}
