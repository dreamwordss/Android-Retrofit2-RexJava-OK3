package com.lbl.networkframe.bean;

import java.util.List;

/**
 * description：
 *
 * @author：ZoranLee create time：17/2/7上午11:55
 */
public class SearchDataList {

    /**
     * code : 0
     * data : {"inputwords":"频道","suggestwords":"频道","content":[{"video":{"pub_time":1480329293000,"is_like":false,"highlight":["频"],"jump_url":"xhc://xiaohongchun/params?video=17150","dcrp":"TA很懒，什么也木有说","like_count":1,"user":{"nick":"快来咯","id":3310531,"is_follow":false,"avatar":"https://wicdn.xiaohongchun.com/cover/28DFD4BAC98D8E08.jpg"},"comment_count":3,"play_time":0,"full_path":"https://wvcdn.xiaohongchun.com/mp4/BC30047DD2C9D629.mp4","play_count":0,"title":"视频啊快来看","comments":[],"id":17150,"cover_url":"https://wicdn.xiaohongchun.com/cover/3F8B1AF484CAC2B4.jpg","share_url":"https://test1.xiaohongchun.com/user/homepageDetailShareInfo?type=1&id=17150&uid=3310531"},"type":"video"}],"channel":[{"title":"1","image":"https://wicdn.xiaohongchun.com/icon/channle_default.jpg","follow_count":"2","target":"xhc://xiaohongchun/params?channelId=1483&info=https%3A%2F%2Ftest1.xiaohongchun.com%2Fsearch_es%2Fvideo_and_sharebuy_count_v2%3Fwords%3D1&data=https%3A%2F%2Ftest1.xiaohongchun.com%2Fsearch_es%2Fvideo_and_sharebuy_list_v2%3Fwords%3D1","content_count":"2"},{"title":"狗狗","image":"https://wicdn.xiaohongchun.com/icon/channle_default.jpg","follow_count":"9","target":"xhc://xiaohongchun/params?channelId=203&info=https%3A%2F%2Ftest1.xiaohongchun.com%2Fsearch_es%2Fvideo_and_sharebuy_count_v2%3Fwords%3D%25E7%258B%2597%25E7%258B%2597&data=https%3A%2F%2Ftest1.xiaohongchun.com%2Fsearch_es%2Fvideo_and_sharebuy_list_v2%3Fwords%3D%25E7%258B%2597%25E7%258B%2597","content_count":"1"}],"isSuggest":false}
     */

    private int code;
    /**
     * inputwords : 频道
     * suggestwords : 频道
     * content : [{"video":{"pub_time":1480329293000,"is_like":false,"highlight":["频"],"jump_url":"xhc://xiaohongchun/params?video=17150","dcrp":"TA很懒，什么也木有说","like_count":1,"user":{"nick":"快来咯","id":3310531,"is_follow":false,"avatar":"https://wicdn.xiaohongchun.com/cover/28DFD4BAC98D8E08.jpg"},"comment_count":3,"play_time":0,"full_path":"https://wvcdn.xiaohongchun.com/mp4/BC30047DD2C9D629.mp4","play_count":0,"title":"视频啊快来看","comments":[],"id":17150,"cover_url":"https://wicdn.xiaohongchun.com/cover/3F8B1AF484CAC2B4.jpg","share_url":"https://test1.xiaohongchun.com/user/homepageDetailShareInfo?type=1&id=17150&uid=3310531"},"type":"video"}]
     * channel : [{"title":"1","image":"https://wicdn.xiaohongchun.com/icon/channle_default.jpg","follow_count":"2","target":"xhc://xiaohongchun/params?channelId=1483&info=https%3A%2F%2Ftest1.xiaohongchun.com%2Fsearch_es%2Fvideo_and_sharebuy_count_v2%3Fwords%3D1&data=https%3A%2F%2Ftest1.xiaohongchun.com%2Fsearch_es%2Fvideo_and_sharebuy_list_v2%3Fwords%3D1","content_count":"2"},{"title":"狗狗","image":"https://wicdn.xiaohongchun.com/icon/channle_default.jpg","follow_count":"9","target":"xhc://xiaohongchun/params?channelId=203&info=https%3A%2F%2Ftest1.xiaohongchun.com%2Fsearch_es%2Fvideo_and_sharebuy_count_v2%3Fwords%3D%25E7%258B%2597%25E7%258B%2597&data=https%3A%2F%2Ftest1.xiaohongchun.com%2Fsearch_es%2Fvideo_and_sharebuy_list_v2%3Fwords%3D%25E7%258B%2597%25E7%258B%2597","content_count":"1"}]
     * isSuggest : false
     */

    public DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String inputwords;
        private String suggestwords;
        private boolean isSuggest;
        /**
         * video : {"pub_time":1480329293000,"is_like":false,"highlight":["频"],"jump_url":"xhc://xiaohongchun/params?video=17150","dcrp":"TA很懒，什么也木有说","like_count":1,"user":{"nick":"快来咯","id":3310531,"is_follow":false,"avatar":"https://wicdn.xiaohongchun.com/cover/28DFD4BAC98D8E08.jpg"},"comment_count":3,"play_time":0,"full_path":"https://wvcdn.xiaohongchun.com/mp4/BC30047DD2C9D629.mp4","play_count":0,"title":"视频啊快来看","comments":[],"id":17150,"cover_url":"https://wicdn.xiaohongchun.com/cover/3F8B1AF484CAC2B4.jpg","share_url":"https://test1.xiaohongchun.com/user/homepageDetailShareInfo?type=1&id=17150&uid=3310531"}
         * type : video
         */

        public List<ContentBean> content;
        /**
         * title : 1
         * image : https://wicdn.xiaohongchun.com/icon/channle_default.jpg
         * follow_count : 2
         * target : xhc://xiaohongchun/params?channelId=1483&info=https%3A%2F%2Ftest1.xiaohongchun.com%2Fsearch_es%2Fvideo_and_sharebuy_count_v2%3Fwords%3D1&data=https%3A%2F%2Ftest1.xiaohongchun.com%2Fsearch_es%2Fvideo_and_sharebuy_list_v2%3Fwords%3D1
         * content_count : 2
         */

        private List<ChannelBean> channel;

        public String getInputwords() {
            return inputwords;
        }

        public void setInputwords(String inputwords) {
            this.inputwords = inputwords;
        }

        public String getSuggestwords() {
            return suggestwords;
        }

        public void setSuggestwords(String suggestwords) {
            this.suggestwords = suggestwords;
        }

        public boolean isIsSuggest() {
            return isSuggest;
        }

        public void setIsSuggest(boolean isSuggest) {
            this.isSuggest = isSuggest;
        }

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public List<ChannelBean> getChannel() {
            return channel;
        }

        public void setChannel(List<ChannelBean> channel) {
            this.channel = channel;
        }

        public static class ContentBean {

            public VideoBean video;

            private ShareBuyBean sharebuy;

            private String type;

            public ShareBuyBean getSharebuy() {
                return sharebuy;
            }

            public ContentBean setSharebuy(ShareBuyBean sharebuy) {
                this.sharebuy = sharebuy;
                return this;
            }

            public VideoBean getVideo() {
                return video;
            }

            public void setVideo(VideoBean video) {
                this.video = video;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public static class VideoBean {
                private long pub_time;
                private boolean is_like;
                private String jump_url;
                private String dcrp;
                private int like_count;
                public double ncover_ratio =0;//宽高比
                /**
                 * nick : 快来咯
                 * id : 3310531
                 * is_follow : false
                 * avatar : https://wicdn.xiaohongchun.com/cover/28DFD4BAC98D8E08.jpg
                 */

                private UserBean user;
                private int comment_count;
                private float play_time;
                private String full_path;
                private int play_count;
                private String title;
                private int id;
                private String cover_url;
                private String share_url;
                private List<String> highlight;
                private List<?> comments;

                public long getPub_time() {
                    return pub_time;
                }

                public void setPub_time(long pub_time) {
                    this.pub_time = pub_time;
                }

                public boolean isIs_like() {
                    return is_like;
                }

                public void setIs_like(boolean is_like) {
                    this.is_like = is_like;
                }

                public String getJump_url() {
                    return jump_url;
                }

                public void setJump_url(String jump_url) {
                    this.jump_url = jump_url;
                }

                public String getDcrp() {
                    return dcrp;
                }

                public void setDcrp(String dcrp) {
                    this.dcrp = dcrp;
                }

                public int getLike_count() {
                    return like_count;
                }

                public void setLike_count(int like_count) {
                    this.like_count = like_count;
                }

                public UserBean getUser() {
                    return user;
                }

                public void setUser(UserBean user) {
                    this.user = user;
                }

                public int getComment_count() {
                    return comment_count;
                }

                public void setComment_count(int comment_count) {
                    this.comment_count = comment_count;
                }

                public float getPlay_time() {
                    return play_time;
                }

                public void setPlay_time(int play_time) {
                    this.play_time = play_time;
                }

                public String getFull_path() {
                    return full_path;
                }

                public void setFull_path(String full_path) {
                    this.full_path = full_path;
                }

                public int getPlay_count() {
                    return play_count;
                }

                public void setPlay_count(int play_count) {
                    this.play_count = play_count;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getCover_url() {
                    return cover_url;
                }

                public void setCover_url(String cover_url) {
                    this.cover_url = cover_url;
                }

                public String getShare_url() {
                    return share_url;
                }

                public void setShare_url(String share_url) {
                    this.share_url = share_url;
                }

                public List<String> getHighlight() {
                    return highlight;
                }

                public void setHighlight(List<String> highlight) {
                    this.highlight = highlight;
                }

                public List<?> getComments() {
                    return comments;
                }

                public void setComments(List<?> comments) {
                    this.comments = comments;
                }

                public static class UserBean {
                    private String nick;
                    private int id;
                    private boolean is_follow;
                    private String avatar;

                    public String getNick() {
                        return nick;
                    }

                    public void setNick(String nick) {
                        this.nick = nick;
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public boolean isIs_follow() {
                        return is_follow;
                    }

                    public void setIs_follow(boolean is_follow) {
                        this.is_follow = is_follow;
                    }

                    public String getAvatar() {
                        return avatar;
                    }

                    public void setAvatar(String avatar) {
                        this.avatar = avatar;
                    }
                }
            }

            public static class ShareBuyBean {

                /**
                 * pub_time : 1481958627776
                 * is_like : false
                 * highlight : ["视频"]
                 * jump_url : xhc://xiaohongchun/params?sd=3014
                 * dcrp : 刚充
                 * like_count : 2
                 * user : {"nick":"起个名真难182","id":182,"is_follow":false,"avatar":"https://i.xiaohongchun.com/@/icon/B51.JPG"}
                 * comment_count : 0
                 * pictures : [{"img_url":"https://wicdn.xiaohongchun.com/cover/3119FDF8740222D1.jpg","id":1380,"s_id":3014},{"img_url":"https://wicdn.xiaohongchun.com/cover/2F18B178D16C5E0D.jpg","id":1381,"s_id":3014},{"img_url":"https://wicdn.xiaohongchun.com/cover/516F2B506FFF5D27.jpg","id":1382,"s_id":3014},{"img_url":"https://wicdn.xiaohongchun.com/cover/225AA6E450D4ADEB.jpg","id":1383,"s_id":3014},{"img_url":"https://wicdn.xiaohongchun.com/cover/3DA288B15AE474B4.jpg","id":1384,"s_id":3014}]
                 * img_desc :
                 * title : 视频
                 * comments : []
                 * id : 3014
                 * cover_url : https://wicdn.xiaohongchun.com/cover/0E10A5251CA1D32D.jpg
                 * share_url : https://test1.xiaohongchun.com/user/homepageDetailShareInfo?type=2&id=3014&uid=182
                 */

                private long pub_time;
                private boolean is_like;
                private String jump_url;
                private String dcrp;
                private int like_count;
                public double width_height_ratio = 0;//
                /**
                 * nick : 起个名真难182
                 * id : 182
                 * is_follow : false
                 * avatar : https://i.xiaohongchun.com/@/icon/B51.JPG
                 */

                private UserBean user;
                private int comment_count;
                private String img_desc;
                private String title;
                private int id;
                private String cover_url;
                private String share_url;
                private List<String> highlight;
                /**
                 * img_url : https://wicdn.xiaohongchun.com/cover/3119FDF8740222D1.jpg
                 * id : 1380
                 * s_id : 3014
                 */

                private List<PicturesBean> pictures;
                private List<?> comments;

                public long getPub_time() {
                    return pub_time;
                }

                public void setPub_time(long pub_time) {
                    this.pub_time = pub_time;
                }

                public boolean isIs_like() {
                    return is_like;
                }

                public void setIs_like(boolean is_like) {
                    this.is_like = is_like;
                }

                public String getJump_url() {
                    return jump_url;
                }

                public void setJump_url(String jump_url) {
                    this.jump_url = jump_url;
                }

                public String getDcrp() {
                    return dcrp;
                }

                public void setDcrp(String dcrp) {
                    this.dcrp = dcrp;
                }

                public int getLike_count() {
                    return like_count;
                }

                public void setLike_count(int like_count) {
                    this.like_count = like_count;
                }

                public UserBean getUser() {
                    return user;
                }

                public void setUser(UserBean user) {
                    this.user = user;
                }

                public int getComment_count() {
                    return comment_count;
                }

                public void setComment_count(int comment_count) {
                    this.comment_count = comment_count;
                }

                public String getImg_desc() {
                    return img_desc;
                }

                public void setImg_desc(String img_desc) {
                    this.img_desc = img_desc;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getCover_url() {
                    return cover_url;
                }

                public void setCover_url(String cover_url) {
                    this.cover_url = cover_url;
                }

                public String getShare_url() {
                    return share_url;
                }

                public void setShare_url(String share_url) {
                    this.share_url = share_url;
                }

                public List<String> getHighlight() {
                    return highlight;
                }

                public void setHighlight(List<String> highlight) {
                    this.highlight = highlight;
                }

                public List<PicturesBean> getPictures() {
                    return pictures;
                }

                public void setPictures(List<PicturesBean> pictures) {
                    this.pictures = pictures;
                }

                public List<?> getComments() {
                    return comments;
                }

                public void setComments(List<?> comments) {
                    this.comments = comments;
                }

                public static class UserBean {
                    private String nick;
                    private int id;
                    private boolean is_follow;
                    private String avatar;

                    public String getNick() {
                        return nick;
                    }

                    public void setNick(String nick) {
                        this.nick = nick;
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public boolean isIs_follow() {
                        return is_follow;
                    }

                    public void setIs_follow(boolean is_follow) {
                        this.is_follow = is_follow;
                    }

                    public String getAvatar() {
                        return avatar;
                    }

                    public void setAvatar(String avatar) {
                        this.avatar = avatar;
                    }
                }

                public static class PicturesBean {
                    private String img_url;
                    private int id;
                    private int s_id;

                    public String getImg_url() {
                        return img_url;
                    }

                    public void setImg_url(String img_url) {
                        this.img_url = img_url;
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public int getS_id() {
                        return s_id;
                    }

                    public void setS_id(int s_id) {
                        this.s_id = s_id;
                    }
                }
            }
        }

        public static class ChannelBean {
            private String title;
            private String image;
            private String follow_count;
            private String target;
            private String content_count;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getFollow_count() {
                return follow_count;
            }

            public void setFollow_count(String follow_count) {
                this.follow_count = follow_count;
            }

            public String getTarget() {
                return target;
            }

            public void setTarget(String target) {
                this.target = target;
            }

            public String getContent_count() {
                return content_count;
            }

            public void setContent_count(String content_count) {
                this.content_count = content_count;
            }
        }
    }
}
