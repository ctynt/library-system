package frame;

import java.awt.*;

/**
 * @version 1.0.0
 * @Author zhy
 * @Date 2023/5/19 8:51
 * @Description
 */
public class GBC extends GridBagConstraints {
    //	初始化左上角位置
    public GBC(int gridx, int gridy){
        this.gridx=gridx;
        this.gridy=gridy;
    }

    //	初始化左上角位置和所占行数和列数
    public GBC(int gridx, int gridy, int gridwidth, int gridheight){
        this.gridx=gridx;
        this.gridy=gridy;
        this.gridwidth=gridwidth;
        this.gridheight=gridheight;
    }

    //	设置对齐方式
    public GBC setAnchor(int anchor){
        this.anchor=anchor;
        return this;
    }

    //	是否拉伸以及拉伸方向
    public GBC setFill(int fill){
        this.fill=fill;
        return this;
    }

    //	x和y方向上的增量（放大或缩小，控件的横向和纵向上的缩放比例）
    public GBC setWeight(double weightx,double weighty){
        this.weightx=weightx;
        this.weighty=weighty;
        return this;
    }
    //	外填充
    public GBC setInsets(int top,int left,int bottom,int right){
        this.insets=new Insets(top, left, bottom, right);
        return this;
    }

    //	内填充,表示在组件首选大小的基础上x方向加上ipadx，y方向上加上ipady，一般用来指定控件大小的
    public GBC setIpad(int ipadx,int ipady){
        this.ipadx=ipadx;
        this.ipady=ipady;
        return this;
    }
}