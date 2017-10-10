package com.idaniu.maga.shopping.bean;

/**
 * frag1��ҳ���ٲ��������ݣ�ÿ����Ӧһ��homebean����
 * Created by yuanbao15 on 2017/10/10.
 */
public class HomeBean {

    private long id;
    private String title;
    private HomeItemBean cpOne;     //��Ӷ����Ǹ���ģ�
    private HomeItemBean cpTwo;
    private HomeItemBean cpThree;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HomeItemBean getCpOne() {
        return cpOne;
    }

    public void setCpOne(HomeItemBean cpOne) {
        this.cpOne = cpOne;
    }

    public HomeItemBean getCpTwo() {
        return cpTwo;
    }

    public void setCpTwo(HomeItemBean cpTwo) {
        this.cpTwo = cpTwo;
    }

    public HomeItemBean getCpThree() {
        return cpThree;
    }

    public void setCpThree(HomeItemBean cpThree) {
        this.cpThree = cpThree;
    }



    //HomeBean�ڲ���
    public class HomeItemBean{
        private long id;
        private String title;
        private String imgUrl;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }
    }

}
