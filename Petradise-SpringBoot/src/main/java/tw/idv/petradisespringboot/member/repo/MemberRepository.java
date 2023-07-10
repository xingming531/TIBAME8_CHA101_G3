package tw.idv.petradisespringboot.member.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import tw.idv.petradisespringboot.member.vo.Member;

import java.util.Optional;

@Component
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByAccountAndPassword(String account, String password);

    Optional<Member> findByAccount(String account);

}
