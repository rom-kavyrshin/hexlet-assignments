package exercise;

import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;
import java.util.List;
import java.util.Map;

public final class App {

    private static final List<Map<String, String>> COMPANIES = Data.getCompanies();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        // BEGIN
        app.get("/companies/{id}", ctx -> {
            int companyId = ctx.pathParamAsClass("id", Integer.class).getOrDefault(-1);

            var company = COMPANIES.stream()
                    .filter((it) -> Integer.parseInt(it.get("id")) == companyId)
                    .findFirst();

            company.ifPresentOrElse(
                    ctx::json,
                    () -> {
                        throw new NotFoundResponse("Company not found");
                    });
        });
        // END

        app.get("/companies", ctx -> {
            ctx.json(COMPANIES);
        });

        app.get("/", ctx -> {
            ctx.result("open something like (you can change id): /companies/5");
        });

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
