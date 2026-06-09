package com.library.management.controller;

import java.time.LocalDate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.library.management.entity.BookIssue;
import com.library.management.service.BookIssueService;
import com.library.management.service.BookService;

@Controller
public class BookIssueController {

    private final BookIssueService bookIssueService;
    private final BookService bookService;

    public BookIssueController(BookIssueService bookIssueService, BookService bookService) {
        this.bookIssueService = bookIssueService;
        this.bookService = bookService;
    }

    @GetMapping("/issues")
    public String listIssues(Model model) {
        model.addAttribute("issues", bookIssueService.findAllIssues());
        return "list-issued";
    }

    @GetMapping("/issue-book/{bookId}")
    public String showIssueForm(@PathVariable("bookId") Long bookId, Model model) {
        var book = bookService.findBookById(bookId);
        var issue = new BookIssue();
        issue.setBook(book);
        issue.setIssueDate(LocalDate.now());
        issue.setDueDate(LocalDate.now().plusDays(14)); // default 14 days borrowing duration

        model.addAttribute("issue", issue);
        model.addAttribute("book", book);
        return "issue-book";
    }

    @PostMapping("/save-issue")
    public String saveIssue(BookIssue issue, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "issue-book";
        }
        bookIssueService.saveIssue(issue);
        return "redirect:/issues";
    }

    @GetMapping("/return-book/{issueId}")
    public String returnBook(@PathVariable("issueId") Long issueId) {
        bookIssueService.returnBook(issueId);
        return "redirect:/issues";
    }
}

