package net.tfgzs.bookstore.book

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import net.tfgzs.common.kit.BeanUtil
import net.tfgzs.passport.user.User
import net.tfgzs.passport.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

import javax.persistence.criteria.*

@Slf4j
@CompileStatic
@RestController
@RequestMapping(value = ["/book"], produces = ["application/json"])
class BookController {
    @Autowired
    BookRepository bookRepository

    @Autowired
    UserRepository userRepository


    @Transactional
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Book add(@RequestBody BookCriteria book) {
        User user = userRepository.findOne(book.userId)
        if (user) {
            Book b = new Book()
            BeanUtil.copyProperties(book, b)
            b.user = user
            Book newBook = bookRepository.save(b)
            return newBook
        } else {
            throw new RuntimeException("用户不存在")
        }
    }

    @Transactional
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void delete(@RequestBody String id) {
        bookRepository.delete(id)
    }

    @Transactional
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Book update(Book b) {
        Book oldBook = bookRepository.findOne(b.id)
        if (oldBook) {
            if (b.bookName) oldBook.bookName = b.bookName
            if (b.bookAuthor) oldBook.bookAuthor = b.bookAuthor
            if (b.bookIntro) oldBook.bookIntro = b.bookIntro
            if (b.spiderUrl) oldBook.spiderUrl = b.spiderUrl
            return bookRepository.save(oldBook)
        } else {
            throw new RuntimeException("图书不存在")
        }
    }

    @RequestMapping(value = "/findById", method = [RequestMethod.POST, RequestMethod.GET])
    public Book findById(String id) {
        Book oldBook = bookRepository.findOne(id)
        if (oldBook) {
            return oldBook
        } else {
            throw new RuntimeException("图书不存在")
        }
    }

    @RequestMapping(value = "/findList/{page}/{size}", method = RequestMethod.POST)
    public Page<Book> findList(@PathVariable int page, @PathVariable int size, @RequestBody BookCriteria bookCriteria) {
        Pageable pageable = new PageRequest(page, size);
        Specification specification = new Specification<Book>() {
            @Override
            Predicate toPredicate(Root<Book> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

                Path<String> bookName = root.get("bookName")
                Path<String> bookAuthor = root.get("bookAuthor")
                Path<String> bookIntro = root.get("bookIntro")

                List<Predicate> searchList = new ArrayList<Predicate>();

                if (bookCriteria.bookName) searchList.add cb.equal(bookName, bookCriteria.bookName)
                if (bookCriteria.bookAuthor) searchList.add cb.equal(bookAuthor, bookCriteria.bookAuthor)
                if (bookCriteria.bookIntro) searchList.add cb.equal(bookIntro, bookCriteria.bookIntro)


                Predicate[] pd = new Predicate[searchList.size()];
                return cb.and(searchList.toArray(pd));
            }
        }
        return bookRepository.findAll(specification, pageable)
    }

}
