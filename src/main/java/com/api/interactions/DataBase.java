package com.api.interactions;

import com.api.exceptions.AutomationException;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Query;
import org.jdbi.v3.core.statement.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Optional;

public class DataBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataBase.class);

    public static Jdbi openConnection(String nameDB) {
        String urlTemplate = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        try {
            LOGGER.debug("Connecting to database with URL: {}", String.format(urlTemplate, nameDB));
            return Jdbi.create(String.format(urlTemplate, nameDB), user, password);
        } catch (Exception e) {
            throw new AutomationException("An error occurred while connecting to the database: ", e);
        }
    }

    public static <T> T executeQuery(String nameDB,
                                     String nameQuery,
                                     Map<String, Object> parameters,
                                     Class<T> className) {
        T queryResult = null;
        try (Handle handle = openConnection(nameDB).open()) {
            try (Query query = handle.createQuery(nameQuery).bindMap(parameters)) {
                Optional<T> optionalResult = query.mapToBean(className).findOne();
                queryResult = optionalResult.orElseThrow(
                        () -> new AutomationException("The query returned no results")
                );
            }
        } catch (Exception e) {
            LOGGER.error("An error occurred while performing the query: ", e);
        }
        return queryResult;
    }

    public static int executeUpdate(String nameDB,
                                    String nameUpdate,
                                    Map<String, Object> parameters) {
        int rowCounter = 0;
        try (Handle handle = openConnection(nameDB).open()) {
            try (Update update = handle.createUpdate(nameUpdate).bindMap(parameters)) {
                rowCounter = update.execute();
            }
        } catch (Exception e) {
            LOGGER.error("An error occurred while performing the query: ", e);
        }
        return rowCounter;
    }
}
