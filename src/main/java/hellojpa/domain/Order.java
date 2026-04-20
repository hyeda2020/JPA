package hellojpa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    // 이런 방식은 테이블 설계에 맞춘 방식으로, 객체 그래프 탐색이 어려움
//    @Column(name = "member_id")
//    private String memberId;

    // 아래처럼 테이블 외래키 객체를 바로 참조하는 것이 객체지향 방식
    private Member member;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
