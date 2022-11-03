package me.bogeun.yajalal.repository.post;

import com.querydsl.jpa.impl.JPAQueryFactory;
import me.bogeun.yajalal.entity.post.Post;
import me.bogeun.yajalal.entity.post.PostType;
import me.bogeun.yajalal.payload.post.PostDto;

import javax.persistence.EntityManager;
import java.util.List;

import static me.bogeun.yajalal.entity.post.QPost.post;


public class PostRepositoryImpl implements PostDynamicRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public PostRepositoryImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Post> getRecentPostListByType(PostType postType, Long typeId, Integer limit) {
        return jpaQueryFactory
                .selectFrom(post)
                .where(post.postType.eq(postType)
                        .and(post.typeId.eq(typeId)))
                .orderBy(post.createdTime.desc())
                .limit(limit)
                .fetch();
    }
}
