package ru.kpfu.entites;

/**
 * Created by danil on 10.11.2016.
 */
public class User {
    private String login;
    private String password;
    private String name;
    private String gender;
    private String country;
    private String aboutYourself;
    private String newsletter;

    public User(String login, String password, String name, String gender, String country, String aboutYourself, String newsletter) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.country = country;
        this.aboutYourself = aboutYourself;
        this.newsletter = newsletter;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getCountry() {
        return country;
    }

    public String getAboutYourself() {
        return aboutYourself;
    }

    public String getNewsletter() {
        return newsletter;
    }
}
