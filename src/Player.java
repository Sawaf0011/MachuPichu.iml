public class Player {
    public static final int NO_ELEMENTS = 0;
    public static final int HAS_BASE = 1;
    public static final int HAS_MIDDLE = 2;
    public static final int HAS_TOP = 3;
    private String name;
    private int score;

    //This field is used to store Michu Pichu status
    //This means what they have achieved in a specific turn
    //If they have not rolled anything mpStatus is NO_ELEMENTS
    //Once they have rolled a 6 if mpStatus == HAS_BASES
    //change mpStatus to HAS_BASE
    //ONCE they rolled a 3 if mpSTATUS == HAS_BASE

    private int mpStatus;
    private int highScore;
    private String password;

    /**
     * Useful method to check for a valid integer
     * @param
     * @return null is not valid otherwise an int
     */

    public Player(String data) {
        String[] playerData = data.split(",");
        this.name = playerData[0];
        score = 0;
        highScore = 0;
        this.password = playerData[0];
        if (playerData.length == 3) {
            Integer hs = Utils.checkValidInt(playerData[1]);
            if (hs != null)
                highScore = hs;
        }
        mpStatus = 0;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void incScore(int score) {
        this.score += score;
    }


    public int getMpStatus() {
        return mpStatus;
    }

    public void setMpStatus(int mpStatus) {
        this.mpStatus = mpStatus;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore() {
        if (score > highScore) {
            highScore = score;
        }
    }

    public String getStatusStr() {
        String status = "";
        switch (mpStatus) {
            case NO_ELEMENTS -> status = "No elements";
            case HAS_BASE -> status = "Has base";
            case HAS_MIDDLE -> status = "Has base. Has middle";
            case HAS_TOP -> status = "Has all elements";
        }
        return status;
    }

    /**
     * This is a version of toString that returns the actual CSV string that you write to the file
     * @return
     */

    public String toCSVString() {
        return name + "," + highScore + "," + password;
    }



    @Override
    public String toString() {
        return "Player: " + name + ". Score: " + score + ". " + getStatusStr() +
                ". High score: " + highScore;
    }


}
