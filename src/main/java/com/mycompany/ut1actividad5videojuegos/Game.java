package com.mycompany.ut1actividad5videojuegos;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Game implements Serializable, Comparable<Game> {
    // game;platform;developer;genre;number_players;rating;release_date;positive_critics;neutral_critics;negative_critics;positive_users;neutral_users;negative_users;metascore;user_score;
    private String name;
    private String platform;
    private String developer;
    private String genre;
    private String numberPlayers;
    private String rating;
    private String releaseDate;
    private int positiveCritics;
    private int neutralCritics;
    private int negativeCritics;
    private int positiveUsers;
    private int neutralUsers;
    private int negativeUsers;
    private int metaScore;
    private int userScore;

    public Game(String name, String platform, String developer, String genre, String numberPlayers, String rating, String releaseDate, int positiveCritics, int neutralCritics, int negativeCritics, int positiveUsers, int neutralUsers, int negativeUsers, int metaScore, int userScore) {
        this.name = name;
        this.platform = platform;
        this.developer = developer;
        this.genre = genre;
        this.numberPlayers = numberPlayers;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.positiveCritics = positiveCritics;
        this.neutralCritics = neutralCritics;
        this.negativeCritics = negativeCritics;
        this.positiveUsers = positiveUsers;
        this.neutralUsers = neutralUsers;
        this.negativeUsers = negativeUsers;
        this.metaScore = metaScore;
        this.userScore = userScore;
    }

    public String getName() {
        return name;
    }

    public String getPlatform() {
        return platform;
    }

    public String getDeveloper() {
        return developer;
    }

    public String getGenre() {
        return genre;
    }

    public String getNumberPlayers() {
        return numberPlayers;
    }

    public String getRating() {
        return rating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Date getReleaseDateAsDate() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
        return dateFormat.parse(releaseDate);
    }

    public int getPositiveCritics() {
        return positiveCritics;
    }

    public int getNeutralCritics() {
        return neutralCritics;
    }

    public int getNegativeCritics() {
        return negativeCritics;
    }

    public int getPositiveUsers() {
        return positiveUsers;
    }

    public int getNeutralUsers() {
        return neutralUsers;
    }

    public int getNegativeUsers() {
        return negativeUsers;
    }

    public int getMetaScore() {
        return metaScore;
    }

    public int getUserScore() {
        return userScore;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setNumberPlayers(String numberPlayers) {
        this.numberPlayers = numberPlayers;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setPositiveCritics(int positiveCritics) {
        this.positiveCritics = positiveCritics;
    }

    public void setNeutralCritics(int neutralCritics) {
        this.neutralCritics = neutralCritics;
    }

    public void setNegativeCritics(int negativeCritics) {
        this.negativeCritics = negativeCritics;
    }

    public void setPositiveUsers(int positiveUsers) {
        this.positiveUsers = positiveUsers;
    }

    public void setNeutralUsers(int neutralUsers) {
        this.neutralUsers = neutralUsers;
    }

    public void setNegativeUsers(int negativeUsers) {
        this.negativeUsers = negativeUsers;
    }

    public void setMetaScore(int metaScore) {
        this.metaScore = metaScore;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }

    @Override
    public String toString() {
        return "Game{" + "name= " + name + ", platform= " + platform + ", developer= " + developer + ", genre= " + genre + ", numberPlayers= " + numberPlayers + ", rating= " + rating + ", releaseDate= " + releaseDate + ", positiveCritics= " + positiveCritics + ", neutralCritics= " + neutralCritics + ", negativeCritics= " + negativeCritics + ", positiveUsers= " + positiveUsers + ", neutralUsers= " + neutralUsers + ", negativeUsers= " + negativeUsers + ", metaScore= " + metaScore + ", userScore= " + userScore + '}' + "\n";
    }

    @Override
    public int compareTo(Game o) {
        try {
            return this.getReleaseDateAsDate().compareTo(o.getReleaseDateAsDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
