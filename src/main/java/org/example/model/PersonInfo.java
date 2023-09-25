package org.example.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "person_info", schema = "public", catalog = "Payments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class PersonInfo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name = "person_id", nullable = true)
    private long personId;
    @Column(name = "phone", nullable = true, length = 20)
    private String phone;

}
