package handler;

import json.JsonUtil;
import model.ResponseError;
import org.jooq.exception.DataAccessException;
import org.jooq.exception.NoDataFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ExceptionHandler;

public class ErrorsHandler {
    private final static Logger logger = LoggerFactory.getLogger(ErrorsHandler.class);

    private final JsonUtil json = new JsonUtil();

    public ExceptionHandler exceptionsHandler() {
        return (e, request, response) -> {
            ResponseError error;

            if (e instanceof NoDataFoundException) {
                response.status(404);

                error = new ResponseError("Requested entity not found", e.getMessage());
            } else if (e instanceof DataAccessException) {
                response.status(500);

                error = new ResponseError("Data access error", e.getMessage());
            } else {
                response.status(500);

                error = new ResponseError("Internal server error", e.getMessage());
            }

            logger.error("Handled server exception", e);

            response.body(json.render(error));
        };
    }
}

