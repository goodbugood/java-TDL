package shali.tdl.jdk.util;

import lombok.Data;

/**
 * 学生成绩信息
 *
 * @author shali
 */
@Data
public class ScoreInfo {
    /**
     * 语文成绩
     */
    private Integer chinese;

    /**
     * 数学成绩
     */
    private Integer math;

    /**
     * 英语成绩
     */
    private Integer english;
}
