package com.wallacela.heroes_api.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;

import static com.wallacela.heroes_api.constants.HeroesConstant.DYNAMO_ENDPOINT;
import static com.wallacela.heroes_api.constants.HeroesConstant.DYNAMO_REGION;

public class HeroesData {
    public static void main(String[] args) throws Exception {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(DYNAMO_ENDPOINT, DYNAMO_REGION))
                .build();

        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable("Heroes_Table");
        Item hero = new Item()
                .withPrimaryKey("id","1")
                .withString("name", "Mulher Maravilha")
                .withString("universe", "dc comics")
                .withNumber("movies", 2);

        Item hero2 = new Item()
                .withPrimaryKey("id","2")
                .withString("name","Viúva Negra")
                .withString("universe", "marvel")
                .withNumber("movies", 5);

        Item hero3 = new Item()
                .withPrimaryKey("id","3")
                .withString("name","Capitã Marvel")
                .withString("universe", "marvel")
                .withNumber("movies",3);


        PutItemOutcome outcome = table.putItem(hero);
        PutItemOutcome outcome2 = table.putItem(hero2);
        PutItemOutcome outcome3 = table.putItem(hero3);
    }
}
