package me.bogeun.yajalal.repository.post;

import me.bogeun.yajalal.entity.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
