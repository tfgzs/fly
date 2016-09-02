package db1

import java.util.function.Consumer
import java.util.stream.Stream

/**
 * 当我们在设计这个框架的时候，
 * 有一个理念一直贯穿始终：“从用户角度出发，简洁、高效、易用、绝不妥协、不忘初心”
 * 做“想象中的框架！”
 */
class DataBase {
    void create() {}

    void drop() {}
}

class Table {
    void createTable() {}

    void deleteTable() {}
}

class Command {
    void insert() {}

    void delete() {}

    void update() {}

    static Stream select(String s) {}
}

class DB {
    DB exec(String s) {
        return this
    }

    DB insert(String sql, Consumer<String> args) {
        args.accept(sql)
        return this
    }

    DB delete() {}

    DB update() {}

    static Stream select(String s) {}
}

class Demo {
    public static void main(String[] args) {
        new DB().exec("select * from bookstore_book")
                .insert("insert into `bookstore_book` (`book_name`,`book_author`) VALUES (?,?)", { sql ->
                    println sql
                })
    }
}
