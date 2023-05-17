package com.fundfun.fundfund.domain.opinion;

import com.fundfun.fundfund.domain.portfolio.Portfolio;
import com.fundfun.fundfund.domain.user.Users;
import com.fundfun.fundfund.domain.vote.Vote;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Opinion {
    @Id
    @GeneratedValue
    @Column(name="opinion_id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name="vote_id")
    private Vote vote;

    @ManyToOne
    private Users user;

    @ManyToOne
    @JoinColumn(name="portfolio_id")
    // portfolio id와 join 필요
    private Portfolio votedFor;
}
