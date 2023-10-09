package com.teamluffy.tripproject.friends.domain.entity;

import com.teamluffy.tripproject.friends.domain.FriendType;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Friends {

  @Id
  private int id;

  @Column()
  private int userId;

  @Enumerated(EnumType.STRING)
  private FriendType type;

  private String friendUserNickname;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
