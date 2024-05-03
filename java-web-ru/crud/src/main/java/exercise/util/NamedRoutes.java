package exercise.util;

public class NamedRoutes {

    public static String rootPath() {
        return "/";
    }

    // BEGIN
    public static String postsPath() {
        return "/posts";
    }

    public static String postsPath(int page) {
        return "/posts?page=" + page;
    }

    public static String postPath(long postId) {
        return postPath(String.valueOf(postId));
    }

    public static String postPath(String postId) {
        return postsPath() + "/" + postId;
    }
    // END
}
