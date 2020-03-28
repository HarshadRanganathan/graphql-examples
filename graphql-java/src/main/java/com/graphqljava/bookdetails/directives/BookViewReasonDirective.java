package com.graphqljava.bookdetails.directives;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.execution.DataFetcherResult;
import graphql.schema.*;
import graphql.schema.idl.SchemaDirectiveWiring;
import graphql.schema.idl.SchemaDirectiveWiringEnvironment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Component
public class BookViewReasonDirective implements SchemaDirectiveWiring {

    @Override
    public GraphQLFieldDefinition onField(SchemaDirectiveWiringEnvironment<GraphQLFieldDefinition> environment) {
        final GraphQLFieldsContainer fieldsContainer = environment.getFieldsContainer();
        final DataFetcher originalDataFetcher = environment.getCodeRegistry().getDataFetcher(fieldsContainer, environment.getFieldDefinition());

        final DataFetcher dataFetcher = DataFetcherFactories.wrapDataFetcher(originalDataFetcher, ((dataFetchingEnvironment, value) -> {
            final String reason = ((Map<String, String>) dataFetchingEnvironment.getLocalContext()).get("reason");
            if(StringUtils.isEmpty(reason)) {
                final Map<String, Object> extensions = new HashMap<>();
                extensions.put("errorCode", "001");
                extensions.put("errorMessage", "Reason required for viewing book store details");

                final GraphQLError graphQLError = GraphqlErrorBuilder.newError()
                        .message("Reason required for viewing book store details")
                        .extensions(extensions)
                        .path(dataFetchingEnvironment.getExecutionStepInfo().getPath())
                        .build();
                return DataFetcherResult.newResult().error(graphQLError).build();
            }
            return value;
        }));
        final FieldCoordinates coordinates = FieldCoordinates.coordinates(fieldsContainer, environment.getFieldDefinition());
        environment.getCodeRegistry().dataFetcher(coordinates, dataFetcher);
        return environment.getElement();
    }
}
