package com.dbms.project.moovi.business.service;

import com.dbms.project.moovi.data.entity.Actor;
import com.dbms.project.moovi.data.entity.Critic;
import com.dbms.project.moovi.data.entity.Fan;
import com.dbms.project.moovi.data.entity.Movie;
import com.dbms.project.moovi.data.repository.ActorRepository;
import com.dbms.project.moovi.data.repository.AdRecruiterRepository;
import com.dbms.project.moovi.data.repository.CriticRepository;
import com.dbms.project.moovi.data.repository.FanRepository;
import com.dbms.project.moovi.data.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
public class FanService extends Utils {

    @Autowired
    private FanRepository fanRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private CriticRepository criticRepository;

    @PostMapping("/api/fan")
    public Fan createFan(@RequestBody Fan fan) {
        return fanRepository.save(fan);
    }

    @GetMapping("/api/fan")
    public List<Fan> findAllFans(@RequestParam(name = "username", required = false) String username) {
        if (username != null)
            return (List<Fan>) fanRepository.findFanByUsername(username);
        return (List<Fan>) fanRepository.findAll();
    }

    @PostMapping("api/like/fan/{username}/movie/{movieId}")
    public void fanLikesMovie(
            @PathVariable("username") String username,
            @PathVariable("movieId") long movieId){
        Movie movie = (Movie) movieRepository.findMovieById(movieId);
        Fan fan = (Fan) fanRepository.findFanByUsername(username);
        fan.likesMovie(movie);
        fanRepository.save(fan);
    }

    @PostMapping("api/dislike/fan/{username}/movie/{movieId}")
    public void fanDislikesMovie(
            @PathVariable("username") String username,
            @PathVariable("movieId") long movieId){
        Movie movie = (Movie) movieRepository.findMovieById(movieId);
        Fan fan = (Fan) fanRepository.findFanByUsername(username);
        fan.dislikesMovie(movie);
        fanRepository.save(fan);
    }

    @PostMapping("api/follow/fan/{username}/actor/{actorId}")
    public void fanFollowsActor(
            @PathVariable("username") String username,
            @PathVariable("actorId") long actorId){

        Actor actor = (Actor) actorRepository.findActorById(actorId);
        Fan fan = (Fan) fanRepository.findFanByUsername(username);
        fan.followsActor(actor);
        fanRepository.save(fan);
    }

    @PostMapping("api/follow/fan/{FanUsername}/critic/{CriticUsername}")
    public void fanFollowsCritic(
            @PathVariable("FanUsername") String fan_username,
            @PathVariable("CriticUsername") String critic_username){

        Critic critic = (Critic) criticRepository.findCriticByUsername(critic_username);
        Fan fan = (Fan) fanRepository.findFanByUsername(fan_username);
        fan.followsCritic(critic);
        fanRepository.save(fan);
    }

    @GetMapping("api/follow/fan/{username}/actorfollowed")
    public List<Actor> listOfActorFollowed(
            @PathVariable("username") String username){
        Fan fan = (Fan) fanRepository.findFanByUsername(username);
        return fan.getActorsFollowed();
    }

    @GetMapping("api/follow/fan/{username}/criticfollowed")
    public List<Critic> listOfCriticFollowed(
            @PathVariable("username") String username){
        Fan fan = (Fan) fanRepository.findFanByUsername(username);
        return fan.getCriticsFollowed();
    }

    @GetMapping("api/follow/fan/{username}/moviesliked")
    public List<Movie> listOfMoviesLiked(
            @PathVariable("username") String username){
        Fan fan = (Fan) fanRepository.findFanByUsername(username);
        return fan.getLikesMovies();
    }

    @GetMapping("api/follow/fan/{username}/moviesdisliked")
    public List<Movie> listOfMoviesDisliked(
            @PathVariable("username") String username){
        Fan fan = (Fan) fanRepository.findFanByUsername(username);
        return fan.getDislikesMovies();
    }

    @PostMapping("/api/follow/fan1/{username1}/fan2/{username2}")
    public void fansFollowingfan(
            @PathVariable(name = "username1") String username1,
            @PathVariable(name = "username2" ) String username2){
        Fan fan1 = fanRepository.findById(fanRepository.findFanIdByUsername(username1)).get();
        Fan fan2 = fanRepository.findById(fanRepository.findFanIdByUsername(username2)).get();
        fan1.followsFan(fan2);
        fanRepository.save(fan1);
    }
}
