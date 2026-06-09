package com.library.management.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.library.management.entity.BookIssue;

public interface BookIssueRepository extends JpaRepository<BookIssue, Long> {

    public List<BookIssue> findByReturnedFalse();

    @Query("SELECT bi FROM BookIssue bi WHERE bi.book.id = ?1 AND bi.returned = false")
    public BookIssue findActiveIssueByBookId(Long bookId);
}

