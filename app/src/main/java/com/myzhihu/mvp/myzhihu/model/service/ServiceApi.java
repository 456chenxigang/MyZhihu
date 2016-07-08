package com.myzhihu.mvp.myzhihu.model.service;

import com.myzhihu.mvp.myzhihu.model.entity.Feed;
import com.myzhihu.mvp.myzhihu.model.entity.StartImage;
import com.myzhihu.mvp.myzhihu.model.entity.StoryDetail;
import com.myzhihu.mvp.myzhihu.model.entity.StoryExtraInfo;
import com.myzhihu.mvp.myzhihu.model.entity.TopicDetail;
import com.myzhihu.mvp.myzhihu.model.entity.WelfareImages;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by CXG on 2016/6/27.
 */
public interface ServiceApi {

    @GET("4/start-image/{density}")
    Observable<StartImage> getStartImage(@Path("density") String density); //返回的Observable用来执行请求的 Observable<StartImage>返回体中的数据

    @GET("4/stories/latest")
    Observable<Feed> fetchLatestMyZhihu();

    @GET("4/news/before/{date}")
    Observable<Feed> fetchPastMyZhihu(@Path("date") String date);

    @GET("4/news/{id}")
    Observable<StoryDetail> getDetailStory(@Path("id") String id);

    @GET("4/story-extra/{id}")
    Observable<StoryExtraInfo> getDetailExtraInfo(@Path("id") String id);

    @GET("4/theme/{id}")
    Observable<TopicDetail> getTopicDetail(@Path("id")String id);

    @GET("4/theme/{topicId}/before/{storyId}")
    Observable<TopicDetail> getPastTopic(@Path("topicId")String topicId,@Path("storyId")String storyId);

    @GET("福利/10/{id}")
    Observable<WelfareImages> getNewWelfareImages(@Path("id")String id);

    @GET("福利/10/{id}")
    Observable<WelfareImages> getPastWelfareImages(@Path("id")String id);
}
