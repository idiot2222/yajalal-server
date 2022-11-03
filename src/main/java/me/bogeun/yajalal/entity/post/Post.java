package me.bogeun.yajalal.entity.post;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 50)
    private String title;

    @Column(length = 500)
    private String content;

    @Column(insertable = false, updatable = false)
    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    @Enumerated(EnumType.STRING)
    private PostType postType;

    private Long typeId;

}
