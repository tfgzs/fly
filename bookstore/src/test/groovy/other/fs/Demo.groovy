package other.fs

/**
 * Created by Liutengfei on 2016/8/2 0002.
 */
class Demo {
    public static void main(String[] args) {
        B.class.getMethods().each {x->
            println x.getParameters()
        }

    }
}
