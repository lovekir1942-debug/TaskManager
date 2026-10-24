import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager{
    private final List<Task> historyList = new ArrayList<>();

    @Override
    public void add(Task task) {
        if (task == null){
            return;
        }
        historyList.add(task);
        if (historyList.size() > 10){
            historyList.remove(0);
        }
    }

    @Override
    public List<Task> getHistory(){
        return new ArrayList<>(historyList);
    }

    @Override
    public void remove(int id){
        for (int i = 0; i < historyList.size(); i++){
            if (historyList.get(i).getId() == id){
                historyList.remove(i);
            }
        }
    }
}
