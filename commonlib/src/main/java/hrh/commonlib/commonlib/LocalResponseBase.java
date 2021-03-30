package hrh.commonlib.commonlib;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LocalResponseBase<T> implements Serializable {
    public int totalCount;
    public List<T> data = new ArrayList<>();
}
