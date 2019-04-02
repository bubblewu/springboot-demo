package com.bubble.domain.entity.param;

import com.bubble.domain.validation.DateTime;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 行程参数信息
 *
 * @author wugang
 * date: 2019-04-02 10:48
 **/
public class TripEntity implements Serializable {
    private static final long serialVersionUID = -3388774931917637154L;

    /**
     * @AssertTrue 针对Boolean类型；被注释的元素必须为true。
     * @AssertFalse 针对Boolean类型；被注释的元素必须为false。
     * @Null 针对任意类型；被注释的元素必须为null。
     * @NotNull 针对任意类型；被注释的元素必须不为null。
     * @Min(value) 针对数值类型；其值必须大于等于指定的最小值。
     * @Max(value) 针对数值类型；其值必须小于等于指定的最大值。
     * @DecimalMin(value) 针对数值类型；被注释的元素必须是一个数字，其值必须大于等于指定的最小值。
     * @DecimalMax(value) 针对数值类型；被注释的元素必须是一个数字，其值必须小于等于指定的最大值。
     * @Digits(integer=整数位数, fraction=小数位数)  针对数值类型；验证注解的元素值的整数位数和小数位数上限。
     * @Size(max=, min=) 针对字符串、Collection、Map、数组等类型；验证注解的元素值的在min和max（包含）指定区间之内，如字符长度、集合大小。
     * @Past 针对java.util.Date, java.util.Calendar, Joda Time类库的日期类型；验证注解的元素值（日期类型）比当前时间早。
     * @Future 针对java.util.Date, java.util.Calendar, Joda Time类库的日期类型；验证注解的元素值（日期类型）比当前时间晚。
     * @Pattern(regexp=正则表达式,flag=标志的模式) 针对String，任何CharSequence的子类型；验证注解的元素值与指定的正则表达式匹配。
     * @Valid 针对任何非原子类型；指定递归验证关联的对象，如用户对象中有个地址对象属性，如果想在验证用户对象时一起验证地址对象的话，在地址对象上加@Valid注解即可级联验证。
     * <p>
     * Hibernate Validator 附加的 constraint：
     * @NotBlank(message =) 针对CharSequence子类型； 验证注解的元素值不为空（不为null、去除首位空格后长度为0），不同于@NotEmpty，@NotBlank只应用于字符串且在比较时会去除字符串的首位空格。
     * @Email(regexp=正则表达式,flag=标志的模式) 针对CharSequence子类型（如String）；验证注解的元素值是Email，也可以通过regexp和flag指定自定义的email格式。
     * @Length(min=,max=) 针对CharSequence子类型；被注释的字符串的大小必须在指定的范围内
     * @NotEmpty 针对CharSequence子类型、Collection、Map、数组类型；验证注解的元素值不为null且不为空（字符串长度不为0、集合大小不为0）。
     * @Range(min=,max=,message=) 针对BigDecimal, BigInteger, CharSequence, byte, short, int, long等原子类型和包装类型；验证注解的元素值在最小值和最大值之间。
     */

    @NotBlank(message = "userId不能为空")
    private String userId;
    @NotBlank(message = "depCode不能为空")
    @Length(min = 3, max = 3, message = "机场三字码长度应为3")
    private String depCode;
    @NotBlank(message = "arrCode不能为空")
    @Length(min = 3, max = 3, message = "机场三字码长度应为3")
    private String arrCode;
    @NotBlank(message = "depTime不能为空")
    @DateTime
    private String depTime;
    @NotBlank(message = "arrTime不能为空")
    @DateTime
    private String arrTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDepCode() {
        return depCode;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

    public String getArrCode() {
        return arrCode;
    }

    public void setArrCode(String arrCode) {
        this.arrCode = arrCode;
    }

    public String getDepTime() {
        return depTime;
    }

    public void setDepTime(String depTime) {
        this.depTime = depTime;
    }

    public String getArrTime() {
        return arrTime;
    }

    public void setArrTime(String arrTime) {
        this.arrTime = arrTime;
    }

    @Override
    public String toString() {
        return "TripEntity{" +
                "userId='" + userId + '\'' +
                ", depCode='" + depCode + '\'' +
                ", arrCode='" + arrCode + '\'' +
                ", depTime='" + depTime + '\'' +
                ", arrTime='" + arrTime + '\'' +
                '}';
    }
}
