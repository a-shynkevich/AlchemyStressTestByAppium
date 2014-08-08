package bn.nook.alchemy.screen;

/**
 * Created by Alecs on 29.07.2014.
 */
public interface IScreenHelper {
    public void start();
    //return current bn.nook.alchemy.screen Id
    int detectedScreen();

    //return state of bn.nook.alchemy.screen
    public boolean isVisible();

}
