package water.dao;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
@TableName("submission")
public class SubmissionDto extends Model<SubmissionDto> {

  @TableId
  private String id;
  @TableField
  private String lang;
  @TableField
  private String runtime;
  @TableField
  private String timestamp;
  @TableField
  private String url;
  @TableField
  private long fetch;

}
