package pers.dc.ols.pojo.bo.center;

import pers.dc.ols.pojo.OrderItem;

public class OrderCommentsBO extends OrderItem {
    private int commentLevel;
    private String content;

    public int getCommentLevel() {
        return commentLevel;
    }

    public void setCommentLevel(int commentLevel) {
        this.commentLevel = commentLevel;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return super.toString() +
                "commentLevel=" + commentLevel +
                ", content='" + content + '\'' +
                '}';
    }
}
