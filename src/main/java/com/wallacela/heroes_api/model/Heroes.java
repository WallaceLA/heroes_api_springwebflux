package com.wallacela.heroes_api.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "Heroes_Table")
@Getter @Setter
public class Heroes {

    private String Id;
    private String name;
    private String universe;
    private int movies;

    /*public Heroes(String id, String name, String universe, int movies) {
        this.Id = id;
        this.name = name;
        this.universe = universe;
        this.movies = movies;
    }*/


    @Id
    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return Id;
    }
    public void setId(String id) {
        Id = id;
    }

    @DynamoDBAttribute(attributeName = "name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName = "universe")
    public String getUniverse() {
        return universe;
    }
    public void setUniverse(String universe) {
        this.universe = universe;
    }

    @DynamoDBAttribute(attributeName = "movies")
    public int getMovies() {
        return movies;
    }
    public void setMovies(int movies) {
        this.movies = movies;
    }

}
