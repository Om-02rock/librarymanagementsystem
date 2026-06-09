package com.library.management.service;

import java.util.List;
import com.library.management.entity.BookIssue;

public interface BookIssueService {
    public List<BookIssue> findAllIssues();
    public List<BookIssue> findActiveIssues();
    public List<Long> findActiveIssueBookIds();
    public BookIssue findActiveIssueByBookId(Long bookId);
    public void saveIssue(BookIssue issue);
    public void returnBook(Long issueId);
}

