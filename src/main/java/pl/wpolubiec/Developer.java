package pl.wpolubiec;

import java.util.List;

public class Developer {

    private String name;
    private List<Developer> followers;

    public Developer(String name, List<Developer> followers) {
        this.name = name;
        this.followers = followers;
    }

    public String getName() {
        return name;
    }

    public List<Developer> getFollowers() {
        return followers;
    }
}
