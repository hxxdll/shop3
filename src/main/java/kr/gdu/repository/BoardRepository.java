package kr.gdu.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import kr.gdu.domain.Board;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//JpaSpecificationExecutor<Board> : 쿼리를 위한 조건 사용할 수 있는 권한.
public interface BoardRepository extends JpaRepository<Board, Integer>,
        JpaSpecificationExecutor<Board>{

    @Query("select coalesce(max(b.num),0) from Board b")
    int maxNum();

    @Modifying // ★ 이거 반드시 추가!
    @Transactional // ★ 이것도 함께 추가!
    @Query("update Board b set b.readcnt = b.readcnt+1 where b.num = :num")
    void addReadcnt(@Param("num") Integer num);
}