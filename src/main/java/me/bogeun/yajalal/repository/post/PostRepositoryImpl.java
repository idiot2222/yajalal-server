package me.bogeun.yajalal.repository.post;

import com.querydsl.jpa.impl.JPAQueryFactory;
import me.bogeun.yajalal.entity.post.Post;
import me.bogeun.yajalal.entity.post.PostType;

import javax.persistence.EntityManager;
import java.util.List;

import static me.bogeun.yajalal.entity.account.QAccount.account;
import static me.bogeun.yajalal.entity.post.QPost.post;


public class PostRepositoryImpl implements PostDynamicRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public PostRepositoryImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Post> getRecentPostListByType(PostType postType, Long typeId, int limit, int page) {
        int offset = (page-1) * limit;

        return jpaQueryFactory
                .selectFrom(post)
                .join(post.account, account).fetchJoin()
                .where(post.postType.eq(postType)
                        .and(post.typeId.eq(typeId)))
                .orderBy(post.createdTime.desc())
                .offset(offset)
                .limit(limit)
                .fetch();
    }

    @Override
    public Integer getPostCount(PostType postType, Long typeId) {
        return jpaQueryFactory
                .select(post.count())
                .from(post)
                .where(post.postType.eq(postType)
                        .and(post.typeId.eq(typeId)))
                .fetchOne()
                .intValue();
    }
}
