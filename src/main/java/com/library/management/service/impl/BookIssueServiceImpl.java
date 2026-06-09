package com.library.management.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.library.management.entity.BookIssue;
import com.library.management.exception.NotFoundException;
import com.library.management.repository.BookIssueRepository;
import com.library.management.service.BookIssueService;

@Service
public class BookIssueServiceImpl implements BookIssueService {

    private final BookIssueRepository bookIssueRepository;

    public BookIssueServiceImpl(BookIssueRepository bookIssueRepository) {
        this.bookIssueRepository = bookIssueRepository;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public List<BookIssue> findAllIssues() {
        return bookIssueRepository.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public List<BookIssue> findActiveIssues() {
        return bookIssueRepository.findByReturnedFalse();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public List<Long> findActiveIssueBookIds() {
        return bookIssueRepository.findByReturnedFalse().stream()
                .map(bi -> bi.getBook().getId())
                .collect(java.util.stream.Collectors.toList());
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public BookIssue findActiveIssueByBookId(Long bookId) {
        return bookIssueRepository.findActiveIssueByBookId(bookId);
    }

    @Transactional
    @Override
    public void saveIssue(BookIssue issue) {
        bookIssueRepository.save(issue);
    }

    @Transactional
    @Override
    public void returnBook(Long issueId) {
        BookIssue issue = bookIssueRepository.findById(issueId)
                .orElseThrow(() -> new NotFoundException(String.format("Issue not found with ID %d", issueId)));
        issue.setReturned(true);
        bookIssueRepository.save(issue);
    }
}

