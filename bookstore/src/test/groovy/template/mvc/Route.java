package template.mvc;

import java.util.function.Supplier;

/**
 * Created by Liutengfei on 2016/8/18 0018.
 */
class Route {
    public Route() {
    }

    Route(String url) {
        this.url = url;
    }

    String url;

    public Route get(Supplier<Route> p) {
        return p.get();
    }

    public Route post(Supplier<Route> p) {
        return p.get();
    }

    public static class Get extends Route {
    }
    public static class Post extends Route {
    }
}
