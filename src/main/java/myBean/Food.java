package myBean;

public class Food {
    private String name;
    private int que;
    private int vote;

    public Food() {
    }

    public Food(String name, int que, int vote) {
        this.name = name;
        this.que = que;
        this.vote = vote;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQue() {
        return que;
    }

    public void setQue(int que) {
        this.que = que;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", que=" + que +
                ", vote='" + vote + '\'' +
                '}';
    }
}
