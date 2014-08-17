package android.hawkencompanionapp.asynctasks;

/**
 * Created by Icarus on 14/08/2014.
 *
 * This is just a simple interface for fragments or activities to use when performaing
 * ASync tasks.
 */
public interface AsyncTaskUpdate {
    public void onAsyncPreComplete();
    public void onAsyncPostComplete();
}
