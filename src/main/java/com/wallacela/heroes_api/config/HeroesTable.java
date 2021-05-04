package com.wallacela.heroes_api.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.*;

import java.util.Arrays;

import static com.wallacela.heroes_api.constants.HeroesConstant.DYNAMO_ENDPOINT;
import static com.wallacela.heroes_api.constants.HeroesConstant.DYNAMO_REGION;

public class HeroesTable {
    public static void main(String[] args) throws Exception {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(DYNAMO_ENDPOINT, DYNAMO_REGION))
                .build();

        DynamoDB dynamoDB = new DynamoDB(client);

        String tableName = "Heroes_Table";

        try {
            Table table = dynamoDB.createTable(tableName,
                    Arrays.asList(new KeySchemaElement("id", KeyType.HASH)),
                    Arrays.asList(new AttributeDefinition("id" , ScalarAttributeType.S)),
                    new ProvisionedThroughput( 5L, 5L));

            table.waitForActive();
            System.out.println("Successo " + table.getDescription().getTableStatus());
        }

        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
