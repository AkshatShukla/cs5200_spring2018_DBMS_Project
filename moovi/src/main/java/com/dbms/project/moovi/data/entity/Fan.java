package com.dbms.project.moovi.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@Entity
public class Fan extends User {

    private String fanDescription;
    
    @ManyToMany
    @JoinTable(name="ActorsFollowed",
    joinColumns= @JoinColumn(name="fan_id", referencedColumnName="userId"),
    inverseJoinColumns= @JoinColumn(name= "actor_id", referencedColumnName="actorId"))
    @JsonIgnore
    private List<Actor> actorsFollowed;
    
    @ManyToMany
    @JoinTable(name="CriticsFollowed",
    joinColumns= @JoinColumn(name="fan_id", referencedColumnName="userId"),
    inverseJoinColumns= @JoinColumn(name= "critic_id", referencedColumnName="criticId"))
    @JsonIgnore
    private List<Critic> criticsFollowed;
    
    @ManyToMany
    @JoinTable(name="Likes",
    joinColumns= @JoinColumn(name="fan_id", referencedColumnName="userId"),
    inverseJoinColumns= @JoinColumn(name= "movie_id", referencedColumnName="movieId"))
    @JsonIgnore
    private List<Movie> likesMovies;

    @ManyToMany
    @JoinTable(name = "Dislikes",
    joinColumns = @JoinColumn(name = "fan_id", referencedColumnName = "userId"),
    inverseJoinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "movieId"))
    @JsonIgnore
    private List<Movie> dislikesMovies;

    public Fan() {
        super();
    }

    public String getFanDescription() {
        return fanDescription;
    }

    public void setFanDescription(String fanDescription) {
        this.fanDescription = fanDescription;
    }
}
