package com.demo.domain.user;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.demo.domain.artist.ArtWork;

import lombok.Data;

@Data
@Entity
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private ArtWork artwork;

    @Temporal(TemporalType.DATE)
    @CreationTimestamp
    private Date createdAt;

    @Temporal(TemporalType.DATE)
    @UpdateTimestamp
    private Date deletedAt;
}
