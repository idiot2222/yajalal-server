package me.bogeun.yajalal.entity.post;

import lombok.Getter;
import lombok.Setter;
import me.bogeun.yajalal.entity.account.Account;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 50)
    private String title;

    @Column(length = 500)
    private String content;

    @Column(updatable = false)
    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    @Enumerated(EnumType.STRING)
    private PostType postType;

    private Long typeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

}
