package com.example.board.service;

import com.example.board.dto.MemberDTO;
import com.example.board.dto.MemoRequestDTO;
import com.example.board.entity.Memo;
import com.example.board.repository.MemoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemoService {

    private final MemoRepository memoRepository;

    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    // 등록
    @Transactional
    public Memo createMemo(MemoRequestDTO requestDTO, MemberDTO member) {
        Memo memo = new Memo();
        memo.setName(member.getName());
        memo.setMemberEmail(member.getEmail());
        memo.setTitle(requestDTO.getTitle());
        memo.setContent(requestDTO.getContent());
        memo.setIsPublic(requestDTO.getIsPublic());
        return memoRepository.save(memo);
    }

    // 삭제
    @Transactional
    public boolean deleteMemo(Long id, MemberDTO member) {
        return memoRepository.findById(id)
                .filter(memo -> memo.getMemberEmail().equals(member.getEmail()))
                .map(memo -> { memoRepository.delete(memo); return true; })
                .orElse(false);
    }

    // 본인 메모 조회 (공개/비공개 상관없이)
    @Transactional(readOnly = true)
    public List<Memo> findMemosByMember(MemberDTO member) {
        return memoRepository.findByMemberEmail(member.getEmail());
    }

    // 공개 메모 조회 (isPublic = true)
    @Transactional(readOnly = true)
    public Page<Memo> findPublicMemos(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("modDate").descending());
        return memoRepository.findByIsPublicTrue(pageable);
    }
}
