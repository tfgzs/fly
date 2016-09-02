package db2
import javax.persistence.Column
import javax.persistence.Table
import java.util.function.Consumer

abstract class Model {

}

@Table(name = "passport_user")
class User extends Model {
    @Column(name = "username")
    String name
    @Column(name = "age")
    Integer age
}
class DB {
    String sql

    static DB from(Class tableName) {
        return this
    }

    static DB column(Consumer<User>... columns) {
        return this
    }

    static DB values(Consumer<User>... columns) {
        return this
    }

    static DB leftJoin(Consumer args) {
        return this
    }
    static DB groupBy(Consumer args) {
        return this
    }

    static DB orderBy(Consumer args) {
        return this
    }

    static DB having(Consumer args) {
        return this
    }

    static DB top(Consumer args) {
        return this
    }

    static DB page(pageSize, pageNum) {
        return this
    }

    static List toList() {
        return this
    }
    static DB where(Consumer<User> where) {
        User user=new User()
        where.accept(user)
        return this
    }
}
//参考C# EF框架   Dos.ORM框架
class Demo {
    public static void main(String[] args) {
        DB.from(User)
                .column({ u -> u.name }, { u -> u.age })
                .where({ u -> u.age > 11 })
    }
}
