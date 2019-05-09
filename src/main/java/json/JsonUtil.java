package json;

import com.google.gson.Gson;
import spark.ResponseTransformer;

public class JsonUtil implements ResponseTransformer {
    @Override
    public String render(Object model) {
        Gson gson = new Gson();

        return gson.toJson(model);
    }
}
