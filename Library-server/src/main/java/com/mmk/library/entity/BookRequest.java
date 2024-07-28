package com.mmk.library.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int requestId;
    @NotBlank
    private String bookName;
    @NotBlank
    private String authorName;
    private String bookEdition;
    private String isBookIssued;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "FK_USER_BOOK_REQUEST_REC"))
    private User user;
}
