package web.petbackend.entity;

import lombok.Data;
import java.util.List;

@Data
public class PageResult<T> {
    private long total;      // 总条数
    private List<T> items;   // 当前页数据

    public PageResult(long total, List<T> items) {
        this.total = total;
        this.items = items;
    }
}
