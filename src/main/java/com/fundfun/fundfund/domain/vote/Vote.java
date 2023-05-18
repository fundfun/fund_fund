package com.fundfun.fundfund.domain.vote;

import com.fundfun.fundfund.domain.opinion.Opinion;
import com.fundfun.fundfund.domain.portfolio.Portfolio;
import com.fundfun.fundfund.domain.post.Post;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "vote")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="vote_id")
    private UUID id;

    @JoinColumn(name = "post_id")
    @OneToOne
    private Post post;

    private LocalDateTime voteStart = LocalDateTime.now();
    private LocalDateTime voteEnd = voteStart.plusDays(30);
    @Builder.Default
    private StVote status = StVote.PROCEED;

<<<<<<< HEAD
   //@OneToMany(mappedBy="vote")
    //private List<Opinion> opinions = new ArrayList<>();

   // @OneToMany(mappedBy="vote")
=======
    //@OneToMany(mappedBy="vote")
    //private List<Opinion> opinions = new ArrayList<>();

    //@OneToMany(mappedBy="vote")
>>>>>>> e8ce8d09d40bef0b5ac3790b9c57421fdfbb9e3c
    //private List<Portfolio> portfolios = new ArrayList<>();

    public void updateStatus() {
        if (LocalDateTime.now().isAfter(voteEnd)) {
            this.status = StVote.END;
        }
    }

    public void linkPost(Post post) {this.post = post;}
}