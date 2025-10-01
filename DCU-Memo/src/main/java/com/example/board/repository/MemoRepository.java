package com.example.board.repository;

import com.example.board.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    List<Memo> findByMemberEmail(String memberEmail);

    // 공개된 메모만 불러오기
    Page<Memo> findByIsPublicTrue(Pageable pageable);
}
