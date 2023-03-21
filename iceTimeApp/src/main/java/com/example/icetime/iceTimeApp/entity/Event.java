package com.example.icetime.iceTimeApp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;

    @Column(name = "event_title", nullable = false)
    @NotBlank(message = "Titolo evento obbligatorio")
    private String eventTitle;

    @Column(name = "event_description", nullable = false)
    private String eventDescription;

    @Column(name = "event_date", nullable = false)
    @NotBlank(message = "Data evento obbligatoria")
    private String eventDate;

    @Column(name = "start_time", nullable = false)
    @NotNull
    private int startTime;

    @Column(name = "end_time", nullable = false)
    @NotNull
    private int endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
