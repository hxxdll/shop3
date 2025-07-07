package kr.gdu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.gdu.domain.Comment;
import kr.gdu.domain.CommentId;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommRepository extends JpaRepository<Comment, CommentId>{
    List<Comment> findByNum(Integer num);//JPA에서 자동으로 SQL구문 생성

    @Query("select COALESCE(max(c.seq),0) from Comment c where c.num = :num")
    int maxseq(int num);
}