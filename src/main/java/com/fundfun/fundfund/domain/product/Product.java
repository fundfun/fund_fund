package com.fundfun.fundfund.domain.product;

import com.fundfun.fundfund.base.BaseTimeEntity;
import com.fundfun.fundfund.domain.order.Orders;
import com.fundfun.fundfund.domain.user.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private UUID id;

    private String title;
    private String crowdStart;
    private String crowdEnd;
    private Long goal;
    private Long currentGoal;
    private String status;
    private String description;
    private String thumbnailRelPath;

//    @OneToOne
//    @JoinColumn(name = "order_id")
//    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users fundManager;

    public String uuidEncode() {
        //UUID encode
        Base64.Encoder encoder = Base64.getEncoder();
        String encodedString = encoder.encodeToString(this.id.toString().getBytes());

        return encodedString;
    }

    public Date toDate(String crowdEnd) {
        Date deadLine = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            deadLine = sdf.parse(crowdEnd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return deadLine;
    }

    public String getThumbnailImgUrl() {
        if (thumbnailRelPath == null) return null;
        return "/gen/" + thumbnailRelPath;
    }


//    private UUID fundManager;

    /**
     * 시작일, 종료일은 업데이트 불가
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public void setGoal(Long goal) {
        this.goal = goal;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCurrentGoal(Long currentGoal){
        this.currentGoal = currentGoal;
    }
    public void setCrowdStart(String crowdStart){
        this.crowdStart = crowdStart;
    }

    public void setCrowdEnd(String crowdEnd){
        this.crowdEnd = crowdEnd;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public void setThumbnailRelPath(String thumbnailRelPath){
        this.thumbnailRelPath = thumbnailRelPath;
    }
}