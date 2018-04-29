import java.util.ArrayList;
import java.io.Serializable;

public class Player implements Serializable {
  public String name;
  public ArrayList<String> words;
  public String playingWord;
  public int life;

  public Player(String name) {
    this.name = name;
    words = new ArrayList<>();
    life = 5;
  }

  public void SetPlayingWord(String word) {
    playingWord = word;
  }

  public String getPlayingWord() {
    return playingWord;
  }

  public void reset() {
    life = 5;
  }

  public void reduceLife() {
    life--;
  }

  public int getLife() {
    return life;
  }

  @Override
  public boolean equals(Object o) {
    boolean bool = false;
    if(o instanceof Player) {
      Player player = (Player) o;
      bool = player.name.equals(this.name);
    }
    return bool;
  }


}
