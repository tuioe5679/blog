package com.tuioe.blog.Entity;

import com.tuioe.blog.dto.MemberDTO;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "member") // 테이블 이름 지정
@Entity // Entity 클래스로 지정 (데이터베이스와 매핑)
@Data // lombok의 어노테이션 (get,set,toString 메소드를 자동 생성)
@EntityListeners(AuditingEntityListener.class)// 엔티티 리스너를 적용 (Auditing 기능을 포함시킨다) @CreateDate 기능 사용시 필요
public class Member {
    @Id// PK 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY)// 기본키 생성 전략을 DB에게 위임(MySQL = Auto INCREMENT)
    @Column(name = "member_id")// 이름 지정
    private Integer idx;

    @Column(nullable = false,length = 30,unique = true)// null X 길이는 30 unique 제약조건 (테이블내의 유일한 값으로 갖는다) 중복 허용 안함
    private String email;

    @Column(nullable = false,length = 300)// null X 길이는 300 (BCryptPasswordEncoder 암호화)
    private String password;

    @Column(nullable = false,length = 30)// null X 길이는 30
    private String name;

    @Column(nullable = false,length = 30,unique = true)// null X 길이는 30 unique 제약조건 (테이블내의 유일한 값으로 갖는다) 중복 허용 안함
    private String nickname;

    @Column(nullable = false,length = 15)// null X 길이는 15
    private String phoneNumber;

    @Column(nullable = false,length = 10)// null X 길이는 10
    private String role;

    @Column(nullable = false)// null X
    @CreatedDate// Entity가 생성되어 저장할때 현재 시간을 자동 생성한다
    private LocalDateTime createDate;

    public static Member create(MemberDTO dto){
        Member member = new Member();
        member.email = dto.getEmail();
        member.password = dto.getPassword();
        member.name = dto.getName();
        member.nickname = dto.getNickname();
        member.phoneNumber = dto.getPhoneNumber();
        member.role = "ROLE_USER";
        return member;
    }
}
