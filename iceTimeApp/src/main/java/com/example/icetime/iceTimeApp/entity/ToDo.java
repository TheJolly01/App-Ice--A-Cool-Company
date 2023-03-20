package com.example.icetime.iceTimeApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "todos")
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long id;

    @Column(name = "todo_date")
    private String todoDate;

    @Column(name = "todo_description")
    private String todoDescription;

    @Column(name = "is_checked")
    private boolean isChecked;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
