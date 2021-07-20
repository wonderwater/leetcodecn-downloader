package water.dao;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("submission_detail")
public class SubmissionDetailDto {

    @TableField
    private String code;
    @TableId
    private String id;
    @TableField
    private String lang;
    @TableField
    private String memory;
    @TableField
    private String codeOutput;
    @TableField
    private String compileError;
    @TableField
    private String expectedOutput;
    @TableField
    private String input;
    @TableField
    private String lastTestcase;
    @TableField
    private String runtimeError;
    @TableField
    private String passedTestCaseCnt;
    @TableField
    private String questionId;
    @TableField
    private String title;
    @TableField
    private String titleSlug;
    @TableField
    private String translatedTitle;
    @TableField
    private String rawMemory;
    @TableField
    private String runtime;
    @TableField
    private String sourceUrl;
    @TableField
    private String statusDisplay;
    @TableField
    private String submissionComment;
    @TableField
    private String timestamp;
    @TableField
    private String totalTestCaseCnt;
}
