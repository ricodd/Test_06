package rico.demo.domain;

import lombok.Data;

import java.util.List;

/**
 * Created by Rico.Chen on 2017/2/19.
 */
@Data
public class Pager {

    /**
     * pagerNO 当前页:由用户决定
     */
    private int pagerNO;

    /**
     * pagerSize 一张页面显示多少条数据: 1)由用户决定 2) 默认值
     */
    private int pagerSize = 3;

    /**
     * pagerCount 总共分多少页：
     * 1) 总共有多少条数据 select count(*) from student;
     * 2) 根据pagerSize来算
     */
    private int pagerCount;

    /**
     * 对应一页的数据
     */
    private List<?> list;

    /**
     *
     * @param pagerNO
     * @param totalCount 总记录数
     */
    public Pager(String pagerNO, int totalCount) {
        this.pagerCount = (totalCount + this.pagerSize - 1) / this.pagerSize;

        if(pagerNO == null) {
            this.pagerNO = 1;
        } else {
            if(Validate.isNumber(pagerNO)) {
                this.pagerNO = Integer.parseInt(pagerNO);
                if(this.pagerNO < 1) {
                    this.pagerNO = 1;
                } else if (this.pagerNO > this.pagerCount) {
                    this.pagerNO = this.pagerCount;
                }
            } else {
                this.pagerNO = 1;
            }
        }
    }

    public Pager(String pagerNO, String pagerSize, int totalCount) {

        if(pagerSize == null) {
            this.pagerSize = 3;
        } else {
            if(Validate.isNumber(pagerSize)) {
                this.pagerSize = Integer.parseInt(pagerSize);
                System.out.println(this.pagerSize);
                if(this.pagerSize != 3 && this.pagerSize != 5 && this.pagerSize != 10) {
                    this.pagerSize = 3;
                }
            } else {
                this.pagerSize = 3;
            }
        }

        this.pagerCount = (totalCount + this.pagerSize - 1) / this.pagerSize;

        if(pagerNO == null) {
            this.pagerNO = 1;
        } else {
            if(Validate.isNumber(pagerNO)) {
                this.pagerNO = Integer.parseInt(pagerNO);
                if(this.pagerNO < 1) {
                    this.pagerNO = 1;
                } else if (this.pagerNO > this.pagerCount) {
                    this.pagerNO = this.pagerCount;
                }
            } else {
                this.pagerNO = 1;
            }
        }

    }


}
