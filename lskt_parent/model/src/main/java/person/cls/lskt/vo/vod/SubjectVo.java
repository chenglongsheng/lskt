package person.cls.lskt.vo.vod;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author helen
 * @since 2020/6/5
 */
@ApiModel("课程分类列表")
@Data
public class SubjectVo {

    @ApiModelProperty(value = "课程分类ID")
    private Long id;

    @ApiModelProperty(value = "课程分类名称")
    private String title;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "课程二级分类列表")
    private List<SubjectVo> children = new ArrayList<>();
}
