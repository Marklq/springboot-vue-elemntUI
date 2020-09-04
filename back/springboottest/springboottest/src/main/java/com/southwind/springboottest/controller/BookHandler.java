package com.southwind.springboottest.controller;

import com.southwind.springboottest.entity.Book;
import com.southwind.springboottest.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookHandler {

    private final BookRepository bookRepository;

    @Autowired
    public BookHandler(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


/*    @GetMapping("/findAll")
    public List<Book> findAll() {
        return bookRepository.findAll();
    }*/

    /**
     * 查询
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/findAll/{page}/{size}")
    public Page<Book> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        PageRequest request = PageRequest.of(page - 1, size);
        return bookRepository.findAll(request);
    }

    /**
     * 保存
     *
     * @param book
     * @return
     */
    @PostMapping("/save")
    public String save(@RequestBody Book book) {
        Book result = bookRepository.save(book);
        if (result != null) {
            return "success";
        } else {
            return "error";
        }
    }

    /**
     * 通过id进行查询
     *
     * @param id
     * @return
     */
    @GetMapping("/findById/{id}")
    public Book findById(@PathVariable("id") Integer id) {
        return bookRepository.findById(id).get();
    }

    /**
     * 改
     *
     * @param book
     * @return
     */
    @PutMapping("/update")
    public String update(@RequestBody Book book) {
        Book result = bookRepository.save(book);
        if (result != null) {
            return "success";
        } else {
            return "error";
        }
    }

    /**
     * 删除
     *
     * @param id
     */
    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") Integer id) {
        bookRepository.deleteById(id);
    }
}
