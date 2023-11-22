package academy.prog;

import java.util.ArrayList;
import java.util.List;

public class JsonMessages {
    private final List<Message> list = new ArrayList<>();

    public JsonMessages(List<Message> sourceList, int fromIndex, String sender) {
        for (int i = fromIndex; i < sourceList.size(); i++) {
            if ((sourceList.get(i).getFrom().equals(sender)) || (sourceList.get(i).getTo().equals("All")) || (sourceList.get(i).getTo().equals(sender))) {
                list.add(sourceList.get(i));
            }
        }
    }
}
