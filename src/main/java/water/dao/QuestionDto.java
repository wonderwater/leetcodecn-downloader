package water.dao;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
@TableName("question")
public class QuestionDto extends Model<QuestionDto> {

  @TableField
  private String title;
  @TableId
  private String titleSlug;
  @TableField
  private String book;
  @TableField
  private String boundTopicId;
  @TableField
  private String categoryTitle;
  @TableField
  private String codeSnippets;
  @TableField
  private String companyTagStats;
  @TableField
  private String content;
  @TableField
  private String contributors;
  @TableField
  private String dailyRecordStatus;
  @TableField
  private String difficulty;
  @TableField
  private String dislikes;
  @TableField
  private String editorType;
  @TableField
  private String enableRunCode;
  @TableField
  private String envInfo;
  @TableField
  private String exampleTestcases;
  @TableField
  private String hints;
  @TableField
  private String isDailyQuestion;
  @TableField
  private String isLiked;
  @TableField
  private String isPaidOnly;
  @TableField
  private String isSubscribed;
  @TableField
  private String judgeType;
  @TableField
  private String judgerAvailable;
  @TableField
  private String langToValidPlayground;
  @TableField
  private String likes;
  @TableField
  private String metaData;
  @TableField
  private String mysqlSchemas;
  @TableField
  private String questionFrontendId;
  @TableField
  private String questionId;
  @TableField
  private String sampleTestCase;
  @TableField
  private String similarQuestions;
  @TableField
  private String solution;
  @TableField
  private String stats;
  @TableField
  private String status;
  @TableField
  private String style;
  @TableField
  private String topicTags;
  @TableField
  private String translatedContent;
  @TableField
  private String translatedTitle;
  @TableField
  private String ugcQuestionId;

}
