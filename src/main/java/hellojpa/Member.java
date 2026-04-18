package hellojpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name") // 컬럼 매핑
    private String name;

    private Integer age;

    @Enumerated(EnumType.STRING) // enum 타입 매핑
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP) // 날짜 타입 매핑
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob // BLOB, CLOB 매핑
    private String description;

//    @Transient // 특정 필드를 컬럼에 매핑하지 않음(매핑 무시)
//    private String temp;
}
